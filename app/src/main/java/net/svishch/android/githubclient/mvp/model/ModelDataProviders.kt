package net.svishch.android.githubclient.mvp.model

import io.reactivex.rxjava3.core.Single
import net.svishch.android.githubclient.ApiHolder
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.model.repo.IGithubRepo
import net.svishch.android.githubclient.mvp.model.repo.IGithubUsersRepo
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo

class ModelDataProviders : ModelData {
    // Работа с данными
    companion object {
        fun newInstance() = ModelDataProviders()
    }

    // Получить репозитарий пользователя
    override fun getUsersRepositories(urlRepos: String): Single<List<GithubRepository>> {
        val repo: IGithubRepo = RetrofitGithubRepositoriesRepo(ApiHolder().api)
        return repo.getRepository(urlRepos)
    }

    // получить список пользователей
    override fun getUsers(): Single<List<GithubUser>> {
        val users: IGithubUsersRepo =   RetrofitGithubUsersRepo(ApiHolder().api)
        return users.getUsers()
    }

}