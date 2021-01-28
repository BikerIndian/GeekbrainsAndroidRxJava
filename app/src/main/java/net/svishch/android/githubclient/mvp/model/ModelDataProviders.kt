package net.svishch.android.githubclient.mvp.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import net.svishch.android.githubclient.ApiHolder
import net.svishch.android.githubclient.App
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.model.repo.IGithubRepo
import net.svishch.android.githubclient.mvp.model.repo.IGithubUsersRepo
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import net.svishch.android.githubclient.mvp.model.entity.room.Database
import net.svishch.android.githubclient.mvp.model.entity.room.RoomGithubRepository
import net.svishch.android.githubclient.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.githubclient.ui.network.AndroidNetworkStatus

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

    private var dataApi = DataApi()
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

    override fun getUsersRepositories(user: GithubUser): Single<List<GithubRepository>> {
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


    // Данные из сети
    class DataApi {
        // получить список пользователей c сети
        fun getUsers(): Single<List<GithubUser>> {
            val users: IGithubUsersRepo = RetrofitGithubUsersRepo(ApiHolder().api)
            return users.getUsers()
        }

        // Получить репозитарий пользователя
        fun getUsersRepositories(user: GithubUser): Single<List<GithubRepository>>? {
            val repo: IGithubRepo = RetrofitGithubRepositoriesRepo(ApiHolder().api)
            return user.reposUrl?.let { repo.getRepository(it) }
        }
    }

    // Данные с базы
    class DataDb() {
        fun getUsers(): Single<List<GithubUser>> {
            return Single.fromCallable {
                db.userDao.getAll()
                    .map { roomUser ->
                        GithubUser(roomUser.id,
                            roomUser.login,
                            roomUser.avatarUrl,
                            roomUser.reposUrl)
                    }
            }.subscribeOn(Schedulers.io())
        }

        fun getUsersRepositories(user: GithubUser): Single<List<GithubRepository>> {
            return Single.fromCallable {

                val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                    ?: throw RuntimeException("No such user in cache")
                db.repositoryDao.findForUser(roomUser.id)
                    .map { GithubRepository(it.id, it.name, it.forksCount) }
            }.observeOn(Schedulers.io())
        }

        fun usersUpdate(users: Single<List<GithubUser>>) {
            users.observeOn(Schedulers.io())
                .subscribe { users ->
                    val roomUsers = users.map { user ->
                        RoomGithubUser(user.id,
                            user.login,
                            user.avatarUrl ?: "",
                            user.reposUrl ?: "")
                    }
                    db.userDao.insert(roomUsers)
                }
        }

        fun repoUpdate(user: GithubUser, repo: Single<List<GithubRepository>>?) {
            repo?.observeOn(Schedulers.io())?.subscribe { repositories ->
                val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                    ?: throw RuntimeException("No such user in cache")
                val roomRepos = repositories.map {
                    RoomGithubRepository(it.id ?: "",
                        it.name ?: "",
                        it.forksCount ?: 0,
                        roomUser.id)
                }
                db.repositoryDao.insert(roomRepos)
            }
        }
    }

}





