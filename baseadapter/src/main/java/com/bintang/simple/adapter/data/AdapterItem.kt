package com.bintang.simple.adapter.data


sealed class BaseAdapterItem {

    data class DefaultAdapterItem(val viewType: Int, val item: Any?): BaseAdapterItem()
}