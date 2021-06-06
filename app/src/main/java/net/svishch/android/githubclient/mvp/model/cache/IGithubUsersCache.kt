package net.svishch.android.githubclient.mvp.model.cache

import io.reactivex.rxjava3.core.Single
import net.svishch.android.githubclient.mvp.model.entity.GithubUser

interface IGithubUsersCache {
    fun getUsers(): Single<List<GithubUser>>
    fun usersUpdate(users: Single<List<GithubUser>>)
}