package net.svishch.android.githubclient.mvp.model

import io.reactivex.rxjava3.core.Single
import net.svishch.android.githubclient.ApiHolder
import net.svishch.android.githubclient.App
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import net.svishch.android.githubclient.mvp.model.repo.IGithubUsersRepo
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import net.svishch.android.githubclient.mvp.model.entity.room.Database
import net.svishch.android.githubclient.mvp.model.cache.IGithubRepositoriesCache
import net.svishch.android.githubclient.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import net.svishch.android.githubclient.mvp.model.network.AndroidNetworkStatus

class ModelDataProviders : ModelData {


    // Работа с данными
    companion object {
        var db = Database.getInstance()
        var networkStatus = false
        fun newInstance() = ModelDataProviders()
        fun initNetMonitor() {
            AndroidNetworkStatus(App.instance).isOnline().subscribe {
                networkStatus = it
            }
        }
    }

    private var dataApi = NetworkApi()
    private var dataDb = DataDb()

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

    // Работа с базой данных
    class DataDb() {
        var usersCache : IGithubUsersCache = RoomGithubUsersCache(db)
        var repositoriesCache : IGithubRepositoriesCache = RoomGithubRepositoriesCache(db)

        fun getUsers(): Single<List<GithubUser>> = usersCache.getUsers()
        fun getUsersRepositories(user: GithubUser): Single<List<GithubRepository>>? = repositoriesCache.getUsersRepositories(user)

        fun usersUpdate(users: Single<List<GithubUser>>) = usersCache.usersUpdate(users)
        fun repoUpdate(user: GithubUser, repo: Single<List<GithubRepository>>?)= repositoriesCache.repoUpdate(user,repo)
    }

}





