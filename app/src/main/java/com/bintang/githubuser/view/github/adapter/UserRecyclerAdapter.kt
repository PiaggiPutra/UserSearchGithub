package com.bintang.githubuser.view.github.adapter

import android.content.res.Resources
import android.view.ViewGroup
import com.bintang.githubuser.view.github.adapter.holder.UserSectionViewHolder
import com.bintang.githubuser.view.github.adapter.holder.UserViewHolder
import com.bintang.githubuser.view.github.adapter.viewmodel.UserAdapterViewModel
import com.bintang.githubuser.view.github.adapter.viewmodel.UserAdapterViewModel.Companion.VIEW_TYPE_ITEM
import com.bintang.githubuser.view.github.adapter.viewmodel.UserAdapterViewModel.Companion.VIEW_TYPE_SECTION
import com.bintang.simple.adapter.BaseRecyclerViewAdapter
import com.bintang.simple.adapter.holder.BaseViewHolder

class UserRecyclerAdapter : BaseRecyclerViewAdapter<UserAdapterViewModel>(::UserAdapterViewModel) {

    override fun createViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder<*, UserAdapterViewModel> =
            when (viewType) {
                VIEW_TYPE_SECTION -> UserSectionViewHolder(parent)
                VIEW_TYPE_ITEM -> UserViewHolder(parent)
                else -> throw Resources.NotFoundException("Not set view type")
            }
}