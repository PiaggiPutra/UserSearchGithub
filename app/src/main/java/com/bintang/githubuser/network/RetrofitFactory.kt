package com.bintang.githubuser.network

import com.bintang.githubuser.GithubApp
import com.bintang.githubuser.util.createRetrofit
import com.bintang.githubuser.util.isOnline

object RetrofitFactory {

    const val baseUrl = "https://api.github.com"

    val githubApi: GithubInterface by lazy {
        createRetrofit(GithubInterface::class.java, baseUrl) {
            GithubApp.context.isOnline()
        }
    }
}