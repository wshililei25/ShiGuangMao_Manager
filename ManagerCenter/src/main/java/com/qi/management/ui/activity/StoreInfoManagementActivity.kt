package com.qi.management.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.sdk.android.oss.*
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback
import com.alibaba.sdk.android.oss.common.OSSLog
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.StoreInfoPresenter
import com.qi.management.presenter.view.StoreInfoView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.Store
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseTakePhotoActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.CityUtil
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_store_info_management.*
import org.devio.takephoto.model.TResult
import java.io.File

/**
 * 门店信息管理
 * Creator Qi
 * Date 2019/1/28
 */
@Route(path = RouterPath.Management.STORE_INFORMATION_MANAGEMENT)
class StoreInfoManagementActivity : BaseTakePhotoActivity<StoreInfoPresenter>(), StoreInfoView, View.OnClickListener {

    private var mLocalFileUrl = ""
    private var mResultUrl: String = ""

    private var mOssSign = ""
    private lateinit var mOssAddress: OssAddress
    private lateinit var mOss: OSS
    private lateinit var mOSSCredentialProvider: OSSCredentialProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_info_management)
        initView()
        initOssInfo()
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {

        mStoreView.onClick(this)
        cityLayout.setOnClickListener {
            hideSoftInput()
            CityUtil.getInstance(this).showPickerView(this) { province, city, district -> storeCityText.text = "$province  $city  $district" }
        }
    }

    /**
     * 初始化oss云存储
     */
    private fun initOssInfo() {

        mOSSCredentialProvider = object : OSSCustomSignerCredentialProvider() {
            override fun signContent(content: String): String {
                // 您需要在这里依照OSS规定的签名算法，实现加签一串字符内容，并把得到的签名传拼接上AccessKeyId后返回
                // 一般实现是，将字符内容post到您的业务服务器，然后返回签名
                // 如果因为某种原因加签失败，描述error信息后，返回nil
                // 以下是用本地算法进行的演示
                Log.d("XiLei", "content=" + content)
                var map = mutableMapOf<String, String>()
                map.put("access-token", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                map.put("content", content)
                mBasePresenter.getOssSign(map)
                return mOssSign
            }
        }

        //获取oss配置数据
        mBasePresenter.getOssAddress()
    }

    override fun onGetOssSignSuccess(result: String) {
        Log.d("XiLei", "mOssSign=" + mOssSign)
        mOssSign = result
    }

    override fun onGetOssAddressSuccess(result: OssAddress) {
        mOssAddress = result
        Log.d("XiLei", "result.endpoint=" + result.endpoint)
        initOss(result.endpoint, result.accessKeyId, result.accessKeySecret, "")
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initData() {
        var map = mutableMapOf<String, String>()
        map.put("id", AppPrefsUtils.getString(BaseConstant.KEY_SHOP_ID))
        mBasePresenter.getStoreInfo(map)
    }

    override fun onGetStoreInfoResult(store: Store) {
        storeIcon.loadUrl(store.imgurl)
        storeNameText.setText(store.storeName)
        storeCityText.text = store.city
        storeAddressText.setText(store.detail)
        storeProfileText.text = store.content
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mStoreView -> showAlertView()
        }
    }

    /**
     * 获取本地图片成功回调
     */
    override fun takeSuccess(result: TResult?) {
        if (!mBasePresenter.checkNetWork()) {
            return
        }
        val localFileUrl = result?.image?.compressPath
        Log.d("XiLei", "localFileUrl=" + localFileUrl);
        mLocalFileUrl = localFileUrl!!

        Log.d("XiLei", "File(mLocalFileUrl).name=" + File(mLocalFileUrl).name)
        //这里要对上传的objectKey(文件名)进行签名后才可上传
        var map = mutableMapOf<String, String>()
        map.put("access-token", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
        map.put("content", File(mLocalFileUrl).name)
        mBasePresenter.getOssSignFile(map)
    }

    override fun onGetOssSignFileSuccess(result: String) {
        Log.d("XiLei", "mOssAddress.bucketName=" + mOssAddress.bucketName)
        Log.d("XiLei", "mLocalFileUrl=" + mLocalFileUrl)
        Log.d("XiLei", "result签名=" + result)
        showLoading()
        OssUploadImg(mOssAddress.bucketName, result, mLocalFileUrl)
    }

    /**
     * 向oss云上传图片
     */
    fun OssUploadImg(bucketName: String, objectKey: String, uploadimage: String) {
        Log.d("XiLei", "OssUploadImg==============")
        // 构造上传请求
        val put = PutObjectRequest(bucketName, objectKey, uploadimage)
        // 异步上传时可以设置进度回调
        put.progressCallback = OSSProgressCallback { request, currentSize, totalSize ->
            Log.d("XiLei", "currentSize: $currentSize totalSize: $totalSize")
        }
        val task = mOss.asyncPutObject(put, object : OSSCompletedCallback<PutObjectRequest, PutObjectResult> {
            override fun onSuccess(request: PutObjectRequest, result: PutObjectResult) {
                Log.d("XiLei", "UploadSuccess")
                Log.d("XiLei", "result=$result")
                Log.d("XiLei", "result.getETag=" + result.eTag)
                Log.d("XiLei", "result.getRequestId=" + result.requestId)
                Log.d("XiLei", "result.getServerCallbackReturnBody=" + result.serverCallbackReturnBody)
                mResultUrl = mOss.presignPublicObjectURL(mOssAddress.bucketName, objectKey)
                Log.d("XiLei", "mResultUrl=" + mResultUrl)
                runOnUiThread(object : Runnable {
                    override fun run() {
                        storeIcon.loadUrl(mResultUrl)
                    }
                })
            }

            override fun onFailure(request: PutObjectRequest, clientExcepion: ClientException?, serviceException: ServiceException?) {
                // 请求异常
                clientExcepion?.printStackTrace()
                if (clientExcepion != null) { // 本地异常
                    Log.e("XiLei", "clientExcepion=" + clientExcepion.message)
                }
                if (serviceException != null) { // 服务异常
                    Log.e("XiLei", "ErrorCode=" + serviceException.errorCode)
                    Log.e("XiLei", "RequestId=" + serviceException.requestId)
                    Log.e("XiLei", "HostId=" + serviceException.hostId)
                    Log.e("XiLei", "RawMessage=" + serviceException.rawMessage)
                }
            }
        })
        // task.cancel(); // 可以取消任务
    }

    private fun initOss(endpoint: String, AccessKeyId: String, SecretKeyId: String, SecurityToken: String) {
        // 在移动端建议使用STS方式初始化OSSClient。
        // 更多信息可查看sample 中 sts 使用方式(https://github.com/aliyun/aliyun-oss-android-sdk/tree/master/app/src/main/java/com/alibaba/sdk/android/oss/app)
        val credentialProvider = OSSStsTokenCredentialProvider(AccessKeyId, SecretKeyId, SecurityToken)
        // 自签名模式

        //该配置类如果不设置，会有默认配置，具体可看该类
        val conf = ClientConfiguration()
        conf.connectionTimeout = 15 * 1000 // 连接超时，默认15秒
        conf.socketTimeout = 15 * 1000 // socket超时，默认15秒
        conf.maxConcurrentRequest = 5 // 最大并发请求数，默认5个
        conf.maxErrorRetry = 2 // 失败后最大重试次数，默认2次
        OSSLog.enableLog()  //开启可以在控制台看到日志
        mOss = OSSClient(applicationContext, endpoint, credentialProvider, conf)
        Log.d("XiLei", "initOss======")
    }

}