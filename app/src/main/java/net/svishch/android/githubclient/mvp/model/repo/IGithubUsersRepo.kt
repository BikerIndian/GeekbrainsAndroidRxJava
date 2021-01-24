package net.svishch.android.githubclient.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import net.svishch.android.githubclient.mvp.model.entity.GithubUser

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}