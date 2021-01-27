package net.svishch.android.githubclient.mvp.model

import io.reactivex.rxjava3.core.Single
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.model.entity.GithubUser

interface ModelData {
    fun getUsersRepositories(urlRepos: String): Single<List<GithubRepository>>
    fun getUsers() : Single<List<GithubUser>>
}