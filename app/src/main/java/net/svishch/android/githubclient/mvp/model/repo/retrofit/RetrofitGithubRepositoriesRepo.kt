package net.svishch.android.githubclient.mvp.model.repo.retrofit

import net.svishch.android.githubclient.mvp.model.api.IDataSource
import net.svishch.android.githubclient.mvp.model.repo.IGithubRepo

class RetrofitGithubRepositoriesRepo(val api: IDataSource) : IGithubRepo {
    override fun getRepository(urlRepo: String) = api.getRepositories(urlRepo)

}