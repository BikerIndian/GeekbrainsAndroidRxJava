package net.svishch.android.githubclient.di.modules

import dagger.Module
import dagger.Provides
import net.svishch.android.githubclient.mvp.model.api.IDataSource
import net.svishch.android.githubclient.mvp.model.network.INetworkStatus
import net.svishch.android.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import net.svishch.android.githubclient.mvp.model.repo.IGithubUsersRepo
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import net.svishch.android.githubclient.mvp.model.cache.IGithubRepositoriesCache
import net.svishch.android.githubclient.mvp.model.cache.IGithubUsersCache

import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache) : IGithubUsersRepo =
        //RetrofitGithubUsersRepo(api, networkStatus, cache)
        RetrofitGithubUsersRepo(api)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache): IGithubRepositoriesRepo =
        //RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
        RetrofitGithubRepositoriesRepo(api)
}