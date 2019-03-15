package com.qi.management.store.costume.list.view

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qi.management.R
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath.Management.COSTUME_LIST
import kotlinx.android.synthetic.main.activity_list_costume.*

/**
 * 服装列表
 * Creator Qi
 * Date 2019/2/28
 */
@Route(path = COSTUME_LIST)
class CostumeListActivity : BaseActivity() {
    /**
     * 服装场馆类型
     */
    enum class SellType(val value: Int) {
        /**售卖区*/
        Sale(0),
        /**共享区*/
        Share(1)
    }

    enum class Sex {
        Man, Woman
    }

    companion object Param {
        const val sell_TYPE = "sell_TYPE"//场馆类型
        const val SEX = "SEX"//场馆类型
        const val CATEGORY: String = "CATEGORY"//服装类型

        fun navigation(sellType: SellType) {
            ARouter.getInstance().build(COSTUME_LIST).withInt(sell_TYPE, sellType.value).navigation()
        }
    }

    private lateinit var manCostumeListFragment: CostumeListFragment
    private lateinit var womanCostumeListFragment: CostumeListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_costume)
        backBtn.setOnClickListener { onBackPressed() }
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.manRadioButton -> {
                    supportFragmentManager.beginTransaction().replace(R.id.contentView, manCostumeListFragment).commit()
                }
                R.id.womanRadioButton -> {
                    supportFragmentManager.beginTransaction().replace(R.id.contentView, womanCostumeListFragment).commit()
                }
            }
        }
        manCostumeListFragment = CostumeListFragment()
        manCostumeListFragment.setParams(intent.getIntExtra(sell_TYPE, 0), Sex.Man.ordinal)
        womanCostumeListFragment = CostumeListFragment()
        womanCostumeListFragment.setParams(intent.getIntExtra(sell_TYPE, 0), Sex.Woman.ordinal)
        manRadioButton.isChecked = true
    }
}