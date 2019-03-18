package com.yizhipin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.FeeRecord
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.data.response.UserType
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.afterLogin
import com.yizhipin.provider.common.isLogined
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.ui.activity.ChargeSetActivity
import com.yizhipin.ui.activity.CustomServiceActivity
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.injection.component.DaggerMainComponent
import com.yizhipin.usercenter.injection.module.MianModule
import com.yizhipin.usercenter.presenter.UserInfoPresenter
import com.yizhipin.usercenter.presenter.view.UserInfoView
import com.yizhipin.usercenter.ui.activity.CreditActivity
import com.yizhipin.usercenter.ui.activity.InvitationActivity
import com.yizhipin.usercenter.ui.activity.UserInfoActivity
import com.yizhipin.usercenter.ui.activity.WalletActivity
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.fragment_me_part.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class MeFragment : BaseMvpFragment<UserInfoPresenter>(), UserInfoView, View.OnClickListener {

    private lateinit var mUserInfo: UserInfo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置Views默认样式
        initViews()
        chargeSettingLayout.setOnClickListener(this::onChargeSettingLayoutListener)//收费设置
        cashPledgeLayout.setOnClickListener(this::onDepositLayoutClickListener)//我的押金
        profileLayout.setOnClickListener(this::onProfileLayoutClickListener)//我的资料、作品
        attentionLayout.setOnClickListener(this::onAttentionLayoutClickListener)//我的关注
        workNoteLayout.setOnClickListener(this::onWorkNoteLayoutClickListener)//工作须知
        phoneLayout.setOnClickListener(this::onPhoneLayoutListener)//客服电话
        settingLayout.setOnClickListener(this::onSettingLayoutListener)//系统设置
        mWorkStatusView.setOnClickListener(this)//上下班
    }

    private fun initViews() {
        mBalanceView.onClick(this)
        mCreditView.onClick(this)
        mInvitationCodeView.onClick(this)
        shareCodeLayout.onClick(this)
        userIconView.onClick(this)
    }

    override fun injectComponent() {
        DaggerMainComponent.builder().activityComponent(mActivityComponent).mianModule(MianModule()).build().inject(this)
        mBasePresenter.mView = this
    }


    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if (isLogined()) {
            mBasePresenter.getUserInfo()
        }
    }

    override fun getUserResult(userInfo: UserInfo) {
        mUserInfo = userInfo
        updateViews(userInfo)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.userIconView -> {
                afterLogin {
                    startActivity<UserInfoActivity>(BaseConstant.KEY_BOOLEAN to true)
                }
            }
            R.id.mBalanceView -> {
                afterLogin {
                    startActivity<WalletActivity>()
                }
            }
            R.id.mCreditView -> {
                afterLogin {
                    startActivity<CreditActivity>(BaseConstant.KEY_INCITATION_CODE to mUserInfo.credit)
                }
            }
            R.id.mInvitationCodeView -> {
                afterLogin {
                    startActivity<InvitationActivity>(BaseConstant.KEY_INCITATION_CODE to mUserInfo.requestCode)
                }
            }
            R.id.shareCodeLayout -> {
                afterLogin {
                    startActivity<InvitationActivity>(BaseConstant.KEY_INCITATION_CODE to mUserInfo.requestCode)
                }
            }
            R.id.mWorkStatusView -> {
                afterLogin {
                    var map = mutableMapOf<String,String>()
                    map.put("uid",AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID))
                    map.put("work",mUserInfo.work.toString())
                    mBasePresenter.postWorkStatus(map)
                }
            }
        }
    }

    /**
     * 上下班打卡成功
     */
    override fun showWorkStatus(workStatusBean: WorkStatusBean) {
        mWorkStatusView.setText(if (workStatusBean.work) R.string.onDuty else R.string.offDuty)
        mUserInfo.work = workStatusBean.work
    }

    /**点击收费设置*/
    private fun onChargeSettingLayoutListener(view: View) {
        startActivity<ChargeSetActivity>()
    }

    //押金
    private fun onDepositLayoutClickListener(view: View) {
        ARouter.getInstance().build(RouterPath.UserCenter.DEPOSIT).navigation()
    }

    //我的资料、作品
    private fun onProfileLayoutClickListener(view: View) {
        ARouter.getInstance().build(RouterPath.UserCenter.PROFILE).navigation()
    }

    //我的关注
    private fun onAttentionLayoutClickListener(view: View) {
        ARouter.getInstance().build(RouterPath.UserCenter.ATTENTION).navigation()
    }

    //工作须知
    private fun onWorkNoteLayoutClickListener(view: View) {
        ARouter.getInstance().build(RouterPath.UserCenter.NOTE).navigation()
    }

    //客服电话
    private fun onPhoneLayoutListener(view: View) {
        startActivity<CustomServiceActivity>()
    }

    //系统设置
    private fun onSettingLayoutListener(view: View) {
        ARouter.getInstance().build(RouterPath.UserCenter.SYSTEM_SETTING).navigation()
    }

    private fun updateViews(userInfo: UserInfo) {
        if (userInfo.type == UserType.Management) {
            topLayout.visibility = GONE
            secondLayout.visibility = GONE
            profileLayout.visibility = GONE
            attentionLayout.visibility = GONE
            shareCodeLayout.visibility = VISIBLE
        } else if (userInfo.type == UserType.Teacher) {
            shareCodeLayout.visibility = GONE
        }
        userIconView.loadUrl(userInfo.imgurl)//头像
        nickNameView.text = userInfo.nickname//昵称
        professionView.text = userInfo.position//职位
        positionView.text = userInfo.level//级别
        nameView.text = userInfo.realName//姓名
        phoneView.text = userInfo.mobile//手机号
        locationView.text = userInfo.storeName//店铺
        remainingSumView.text = userInfo.amount//余额
        creditScoreView.text = userInfo.credit//信用
        invitationCodeView.text = userInfo.requestCode//邀请码
        mWorkStatusView.setText(if (userInfo.work) R.string.onDuty else R.string.offDuty)//工作状态
    }

    override fun onEditUserResult(result: UserInfo) {
    }

    override fun onGetCartSuccess(result: Int) {
    }

    override fun getFeeRecordListSuccess(result: MutableList<FeeRecord>) {
    }

    override fun onGetOssSignSuccess(result: String) {
    }

    override fun onGetOssSignFileSuccess(result: String) {
    }

    override fun onGetOssAddressSuccess(result: OssAddress) {
    }
}