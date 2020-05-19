package com.bintang.githubuser.view.github.adapter.holder

import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_user.*
import com.bintang.githubuser.R
import com.bintang.githubuser.data.GithubUser
import com.bintang.githubuser.util.loadUrl
import com.bintang.githubuser.view.github.adapter.viewmodel.UserAdapterViewModel
import com.bintang.simple.adapter.holder.BaseViewHolder

class UserViewHolder(parent: ViewGroup) :
        BaseViewHolder<GithubUser, UserAdapterViewModel>(R.layout.item_user, parent) {

    override fun UserAdapterViewModel.onInitViewModel() {
        containerView.setOnClickListener {
            this.onClickUserItem(adapterPosition)
        }
    }

    override fun onBindViewHolder(item: GithubUser?) {
        img_user_picture.loadUrl(item?.avatarUrl, R.drawable.ic_launcher_foreground)
        tv_user_name.text = item?.login
        tv_user_score.text = context.getString(R.string.message_user_score, item?.score ?: 0.0)
        img_user_like.isSelected = item?.isLike ?: false
    }
}