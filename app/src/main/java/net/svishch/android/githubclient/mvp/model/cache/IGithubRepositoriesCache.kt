package ru.geekbrains.githubclient.mvp.model.cache

import io.reactivex.rxjava3.core.Single
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.model.entity.GithubUser

interface IGithubRepositoriesCache {
    fun getUsersRepositories(user: GithubUser): Single<List<GithubRepository>>?
    fun repoUpdate(user: GithubUser, repo: Single<List<GithubRepository>>?)
}