package com.bintang.githubuser.data.source.search

import io.reactivex.Single
import com.bintang.githubuser.data.GithubUser
import com.bintang.githubuser.db.GithubRoomDatabase
import com.bintang.githubuser.db.GithubUserDao

class GithubSearchLocalDataSource(githubRoomDatabase: GithubRoomDatabase) {

    private val githubUserDao: GithubUserDao = githubRoomDatabase.githubUserDao()

    fun insertGithubUser(item: GithubUser) {
        githubUserDao.insert(item)
    }

    fun getAllLikeUser() =
            githubUserDao.selectUser()

    fun searchUserLocal(name: String): Single<MutableList<GithubUser>> =
            githubUserDao.searchUser(name)

    fun removeUser(login: String) {
        githubUserDao.deleteUser(login)
    }
}