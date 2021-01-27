package net.svishch.android.githubclient.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository

interface IGithubRepo {
    fun getRepository(urlRepo: String): Single<List<GithubRepository>>
}