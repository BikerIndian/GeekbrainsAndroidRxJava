package net.svishch.android.githubclient.mvp.model.repo.retrofit

import net.svishch.android.githubclient.mvp.model.api.IDataSource
import net.svishch.android.githubclient.mvp.model.repo.IGithubRepositoriesRepo

class RetrofitGithubRepositoriesRepo(val api: IDataSource) : IGithubRepositoriesRepo {
    override fun getRepository(urlRepo: String) = api.getRepositories(urlRepo)

}