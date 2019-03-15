package com.yizhipin.base.mvp.view

/**
 * Creator Qi
 * Date 2019/3/3
 */
interface ListView<Data> {

    fun add(data: MutableList<Data>)

    fun remove(data: Data)

    fun remove(data: MutableList<Data>)

    fun getAll(): MutableList<Data>

    fun getItem(position: Int): Data?
}