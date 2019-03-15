package com.qi.management.store.common_detail.view

import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.text.TextUtils
import android.view.View.GONE
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qi.management.R
import com.qi.management.bean.CommonDetailBean
import com.qi.management.store.common_detail.adapter.CommonItemAdapter
import com.qi.management.store.common_detail.dagger.CombosDetailModule
import com.qi.management.store.common_detail.dagger.DaggerCombosDetailComponent
import com.qi.management.store.common_detail.presenter.CombosDetailPresenterImpl
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.widgets.BannerImageLoader
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.provider.router.RouterPath.Management.Detail
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.item_combos_detail_banner_holder.*
import kotlinx.android.synthetic.main.item_detail_costumes.*
import kotlinx.android.synthetic.main.item_detail_package_info.*

/**
 * 详情页（套餐、产品）
 */
@Route(path = Detail)
class CommonDetailActivity : BaseMvpActivity<CombosDetailPresenterImpl>(), CombosDetailView {

    companion object {
        const val PARAM_COMBOS_BEAN = "PARAM_COMBOS_BEAN"
        const val PAGE_TYPE = "PAGE_TYPE"

        fun navigation(bean: CommonDetailBean, pageType: PageType) {
            ARouter.getInstance().build(RouterPath.Management.Detail)
                    .withSerializable(CommonDetailActivity.PARAM_COMBOS_BEAN, bean)
                    .withInt(PAGE_TYPE, pageType.ordinal)
                    .navigation()
        }
    }

    enum class PageType {
        /**套餐*/
        Combos,
        /**产品*/
        Production,
        /**服装*/
        Costume
    }

    private val suggestionAdapter = CommonItemAdapter()

    override fun injectComponent() {
        DaggerCombosDetailComponent.builder().activityComponent(mActivityComponent).combosDetailModule(CombosDetailModule(this)).build().inject(this)
    }

    override fun onCreateView(): Int {
        return R.layout.activity_combos_detail
    }

    override fun initView(savedInstanceState: Bundle?) {
        costumeRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))
        costumeRecyclerView.adapter = suggestionAdapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        val bean = intent.getSerializableExtra(PARAM_COMBOS_BEAN) as CommonDetailBean
        val pageType = intent.getIntExtra(PAGE_TYPE, -1)
        mBasePresenter.bean = bean
        mBasePresenter.pageType = pageType
        when (pageType) {
            PageType.Combos.ordinal -> {//套餐详情
//                titleDetailView.setTitle(R.string.title_combos_detail)
                costumesLayout.visibility = GONE//隐藏服装推荐View
            }
            PageType.Production.ordinal -> {
                //产品详情
//                titleDetailView.setTitle(R.string.title_production_detail)
                combosInfoLayout.visibility = GONE//隐藏套餐信息View
                costumesLayout.visibility = GONE//隐藏服装推荐View
                moreLayout.visibility = GONE//隐藏评价View
            }
            PageType.Costume.ordinal -> {
                //服装详情
//                titleDetailView.setTitle(R.string.title_comtume_detail)
                combosInfoLayout.visibility = GONE//隐藏套餐信息View
                moreLayout.visibility = GONE//隐藏评价View
            }
            //设置图片集合
            //banner设置方法全部调用完毕时最后调用
        }
        //banner设置方法全部调用完毕时最后调用
        mBasePresenter.getDetail()
        if (pageType == PageType.Costume.ordinal)
            mBasePresenter.getSuggestion()
    }

    override fun show(bean: CommonDetailBean) {
        titleText.text = bean.title
        countText.text = String.format(resources.getString(R.string.sellCount), bean.sellCount)
        priceText.text = "￥ " + bean.price
        marketPriceText.text = "￥ " + bean.marketPrice
        storeIcon.loadUrl(if (TextUtils.isEmpty(bean.storeImgurl)) "" else bean.storeImgurl)
        storeNameText.text = bean.storeName
        clothNumText.text = String.format(resources.getString(R.string.clothNum), bean.clothCount)
        negativeNumText.text = String.format(resources.getString(R.string.negativeNum), bean.filmCount)
        enterNumText.text = String.format(resources.getString(R.string.enterNum), bean.rucheCount)
        detailImageView.text = Html.fromHtml(bean.content, MImageGetter(detailImageView), null)
        val bannerImages = arrayListOf<String>()
        if (!TextUtils.isEmpty(bean.imgurls))
            bean.imgurls.split(",").forEach {
                bannerImages.add(it)
            }
        //设置图片集合
        initBanner()
        banner.setImages(bannerImages)
        banner.start()
    }

    override fun showSuggestion(data: MutableList<CommonDetailBean>) {
        suggestionAdapter.addAll(data)
    }

    private fun initBanner() {
        //设置图片加载器
        banner.setImageLoader(BannerImageLoader())
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage)
        //设置自动轮播，默认为true
        banner.isAutoPlay(true)
        //设置轮播时间
        banner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER)
    }


    /**
     * html中图片地址处理
     */
    inner class MImageGetter(internal var container: TextView) : Html.ImageGetter {

        override fun getDrawable(source: String): Drawable {
            val drawable = LevelListDrawable()
            /* Glide.with(container).load(source).into(object : SimpleTarget<Drawable>() {
                 override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                     val scale = BigDecimal(resource.intrinsicWidth).divide(BigDecimal(resource.intrinsicHeight), 2, RoundingMode.HALF_UP)
                     drawable.addLevel(1, 1, resource)
                     drawable.setBounds(0, 0, container.width, BigDecimal(container.width).divide(scale, 0, RoundingMode.HALF_UP).toInt())
                     drawable.level = 1
                     container.invalidate()
                     container.text = container.text
                 }
             })*/
            return drawable
        }
    }

}
