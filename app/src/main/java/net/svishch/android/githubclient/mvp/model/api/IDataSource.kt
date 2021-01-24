package net.svishch.android.githubclient.mvp.model.api

import io.reactivex.rxjava3.core.Single
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import retrofit2.http.GET

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}