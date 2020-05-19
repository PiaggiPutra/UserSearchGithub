package com.bintang.githubuser.data.source.search

import io.reactivex.Single
import com.bintang.githubuser.data.GithubResponse
import com.bintang.githubuser.network.GithubInterface

class GithubSearchRemoteDataSource(private val githubApi: GithubInterface) {

    fun searchUser(name: String, page: Int, perPage: Int): Single<GithubResponse> =
            githubApi.searchUser(name, page, perPage)
}