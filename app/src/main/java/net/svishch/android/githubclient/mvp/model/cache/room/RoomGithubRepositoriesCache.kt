package ru.geekbrains.githubclient.mvp.model.cache.room

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import net.svishch.android.githubclient.mvp.model.ModelDataProviders
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.model.entity.room.Database
import net.svishch.android.githubclient.mvp.model.entity.room.RoomGithubRepository
import net.svishch.android.githubclient.mvp.model.cache.IGithubRepositoriesCache

class RoomGithubRepositoriesCache(var db: Database) : IGithubRepositoriesCache {

    // Получить репозитарий пользователя

    override fun getUsersRepositories(user: GithubUser): Single<List<GithubRepository>> {
        return Single.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                ?: throw RuntimeException("No such user in cache")
            db.repositoryDao.findForUser(roomUser.id)
                .map { GithubRepository(it.id, it.name, it.forksCount) }
        }.observeOn(Schedulers.io())
    }

    override fun repoUpdate(user: GithubUser, repo: Single<List<GithubRepository>>?) {
        repo?.observeOn(Schedulers.io())?.subscribe { repositories ->
            val roomUser = user.login?.let { ModelDataProviders.db.userDao.findByLogin(it) }
                ?: throw RuntimeException("No such user in cache")
            val roomRepos = repositories.map {
                RoomGithubRepository(it.id ?: "",
                    it.name ?: "",
                    it.forksCount ?: 0,
                    roomUser.id)
            }
            ModelDataProviders.db.repositoryDao.insert(roomRepos)
        }
    }
}