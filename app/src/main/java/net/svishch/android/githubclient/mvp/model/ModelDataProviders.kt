package net.svishch.android.githubclient.mvp.model

import io.reactivex.rxjava3.core.Single
import net.svishch.android.githubclient.ApiHolder
import net.svishch.android.githubclient.App
import net.svishch.android.githubclient.mvp.model.db.DataDb
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.model.network.AndroidNetworkStatus
import net.svishch.android.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import net.svishch.android.githubclient.mvp.model.repo.IGithubUsersRepo
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import javax.inject.Inject

class ModelDataProviders : ModelData {

    @Inject
    lateinit var dataDb: DataDb

    // Работа с данными
    companion object {
        var networkStatus = false
        fun newInstance() = ModelDataProviders().apply {
            App.instance.appComponent.inject(this)
        }

        fun initNetMonitor() {
            AndroidNetworkStatus(App.instance).isOnline().subscribe {
                networkStatus = it
            }
        }
    }

    private var dataApi = NetworkApi()

    // получить список пользователей
    override fun getUsers(): Single<List<GithubUser>> {
        if (networkStatus) {
            val users = dataApi.getUsers()
            dataDb.usersUpdate(users)
            return users
        } else {
            return dataDb.getUsers()
        }
    }

    override fun getUsersRepositories(user: GithubUser): Single<List<GithubRepository>>? {
        if (networkStatus) {
            var repo = dataApi.getUsersRepositories(user)
            dataDb.repoUpdate(user, repo)

            return repo!!
        } else {

            return dataDb.getUsersRepositories(user)
        }

        return dataApi.getUsersRepositories(user)!!
    }

    fun getNetworkStatus(): Single<Boolean> {
        return AndroidNetworkStatus(App.instance).isOnlineSingle()
    }


    // Работа с сетью
    class NetworkApi {
        // получить список пользователей c сети
        fun getUsers(): Single<List<GithubUser>> {
            val users: IGithubUsersRepo = RetrofitGithubUsersRepo(ApiHolder().api)
            return users.getUsers()
        }

        // Получить репозитарий пользователя
        fun getUsersRepositories(user: GithubUser): Single<List<GithubRepository>>? {
            val repo: IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(ApiHolder().api)
            return user.reposUrl?.let { repo.getRepository(it) }
        }
    }


}





