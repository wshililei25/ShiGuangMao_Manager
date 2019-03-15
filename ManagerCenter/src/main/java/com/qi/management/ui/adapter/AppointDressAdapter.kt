package com.qi.management.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.yizhipin.base.data.response.AppiontDress
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_appoint_dress_item.view.*

/**
 * Created by ${XiLei} on 2018/8/19.
 * 指定服装
 */
class AppointDressAdapter(var context: Context) : BaseRecyclerViewAdapter<AppiontDress, AppointDressAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_appoint_dress_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val modle = dataList[position]

        with(modle) {
            holder.itemView.mTitleTv.text = title
            holder.itemView.mWomenIv.loadUrl(womenImage)
            holder.itemView.mManIv.loadUrl(manImage)
        }

       /* holder.itemView.mWomenDressView.onClick {
            (context as Activity).startActivityForResult<DressListActivity>(1000
                    , GoodsConstant.KEY_DRESS_SHOP_STATUS to DressShopStatus.DRESS_SHOP_SHARE
                    , BaseConstant.KEY_DRESS to 0, BaseConstant.KEY_DRESS_POSITION to position)
        }

        holder.itemView.mManDressView.onClick {
            (context as Activity).startActivityForResult<DressListActivity>(1001
                    , GoodsConstant.KEY_DRESS_SHOP_STATUS to DressShopStatus.DRESS_SHOP_SHARE
                    , BaseConstant.KEY_DRESS to 1, BaseConstant.KEY_DRESS_POSITION to position)
        }*/

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}