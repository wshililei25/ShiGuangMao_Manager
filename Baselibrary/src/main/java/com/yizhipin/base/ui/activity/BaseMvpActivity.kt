package com.yizhipin.base.ui.activity

import android.os.Bundle
import com.yizhipin.base.R
import com.yizhipin.base.common.BaseApplication
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.base.injection.component.DaggerActivityComponent
import com.yizhipin.base.injection.moudule.ActivityModule
import com.yizhipin.base.injection.moudule.LifecycleProviderModule
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.base.utils.ToastUtils
import com.yizhipin.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/5/28.
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mBasePresenter: T
    lateinit var mActivityComponent: ActivityComponent
    lateinit var mDialogLoading: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDialogLoading = ProgressLoading.create(this)
        if (onCreateView() != 0)
            setContentView(onCreateView())
        initView(savedInstanceState)
        initData(savedInstanceState)
    }

    open fun onCreateView(): Int {
        return 0
    }

    open fun initView(savedInstanceState: Bundle?) {
    }

    open fun initData(savedInstanceState: Bundle?) {
        initActivityInjection()
        injectComponent()
    }

    /**
     *
     */
    abstract fun injectComponent()

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).mAppComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() {
        mDialogLoading.showLoading()
    }

    override fun hideLoading() {
        mDialogLoading.hideLoading()
    }

    override fun onError(mes: String) {
        toast(mes)
    }
}