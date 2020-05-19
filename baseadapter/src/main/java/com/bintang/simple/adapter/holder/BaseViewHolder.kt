package com.bintang.simple.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bintang.simple.adapter.viewmodel.BaseAdapterViewModel

abstract class BaseViewHolder<in ITEM : Any, VIEW_MODEL : BaseAdapterViewModel>(
        layoutRes: Int,
        parent: ViewGroup,
        protected val context: Context = parent.context) : AndroidViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

    private lateinit var _viewModel: VIEW_MODEL

    var viewModel: VIEW_MODEL
        get() = _viewModel
        set(value) {
            _viewModel = value
            _viewModel.onInitViewModel()
        }

    fun checkItemAndBindViewHolder(item: Any?) {
        try {
            onBindViewHolder(item as? ITEM)
        } catch (e: Exception) {
            onBindViewHolder(null)
        }
    }

    
    abstract fun VIEW_MODEL.onInitViewModel()

  
    abstract fun onBindViewHolder(item: ITEM?)
}