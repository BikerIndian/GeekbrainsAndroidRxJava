package net.svishch.android.githubclient.mvp.model.repo.retrofit

import io.reactivex.rxjava3.schedulers.Schedulers
import net.svishch.android.githubclient.mvp.model.api.IDataSource
import net.svishch.android.githubclient.mvp.model.repo.IGithubUsersRepo

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}