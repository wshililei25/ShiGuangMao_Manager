package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.alibaba.sdk.android.oss.*
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback
import com.alibaba.sdk.android.oss.common.OSSLog
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseTakePhotoActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.AuthenticationPresenter
import com.yizhipin.usercenter.presenter.view.AuthenticationView
import kotlinx.android.synthetic.main.activity_authentication.*
import org.devio.takephoto.model.TResult
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.File

/**
 * Creator Qi
 * Date 2018/12/18
 * 实名认证
 */
class AuthenticationActivity : BaseTakePhotoActivity<AuthenticationPresenter>(), AuthenticationView, View.OnClickListener {

    private var mLocalFileUrl = ""
    private var mResultFrontUrl: String = ""
    private var mResultReverseUrl: String = ""
    private var mIsFront: Boolean = false

    private var mOssSign = ""
    private lateinit var mOssAddress: OssAddress
    private lateinit var mOss: OSS
    private lateinit var mOSSCredentialProvider: OSSCredentialProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        initView()
        initOssInfo()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {
        mFrontCardPhoto.onClick(this)
        mReverseCardPhoto.onClick(this)
        mBtn.onClick(this)
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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mFrontCardPhoto -> {
                mIsFront = true
                showAlertView()
            }
            R.id.mReverseCardPhoto -> {
                mIsFront = false
                showAlertView()
            }
            R.id.mBtn -> {

                if (mNameEt.text.toString().isNullOrBlank()) {
                    toast("请输入您的真实姓名")
                    return
                }
                if (mCardEt.text.toString().isNullOrBlank()) {
                    toast("请输入您的身份证号码")
                    return
                }
                if (mResultFrontUrl.isNullOrBlank()) {
                    toast("请上传身份证正面照")
                    return
                }
                if (mResultReverseUrl.isNullOrBlank()) {
                    toast("请上传身份证返面照")
                    return
                }

                var map = mutableMapOf<String, String>()
                map.put("id", AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID))
                map.put("realName", mNameEt.text.toString().trim())
                map.put("idCard", mCardEt.text.toString().trim())
                map.put("cardBefore", mResultFrontUrl)
                map.put("cardAfter", mResultReverseUrl)
                mBasePresenter.updateUserInfo(map)
            }
        }
    }

    override fun onUpdateUserInfoSuccess(result: UserInfo) {

        AppPrefsUtils.putString(BaseConstant.KEY_SP_REAL_NAME, result?.realName  ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SP_CARD, result?.idCard   ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SP_FRONT, result?.cardBefore   ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SP_REVERSE, result?.cardAfter   ?: "")
        startActivity<TeacherEnterDatumActivity>()
        finish()
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
                if (mIsFront) {
                    mResultFrontUrl = mOss.presignPublicObjectURL(mOssAddress.bucketName, objectKey)
                } else {
                    mResultReverseUrl = mOss.presignPublicObjectURL(mOssAddress.bucketName, objectKey)
                }
                Log.d("XiLei", "mResultFrontUrl=" + mResultFrontUrl)
                Log.d("XiLei", "mResultReverseUrl=" + mResultReverseUrl)
                runOnUiThread(object : Runnable {
                    override fun run() {
                        if (mIsFront) {
                            mFrontCardPhoto.loadUrl(mResultFrontUrl)
                        } else {
                            mReverseCardPhoto.loadUrl(mResultReverseUrl)
                        }
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

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(mes: String) {
    }
}