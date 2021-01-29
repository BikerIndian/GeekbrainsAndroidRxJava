package ru.geekbrains.githubclient.mvp.model.cache.room

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.model.entity.room.Database
import net.svishch.android.githubclient.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache

class RoomGithubUsersCache(var db: Database) : IGithubUsersCache {

    override fun getUsers(): Single<List<GithubUser>> {
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

    override fun usersUpdate(users: Single<List<GithubUser>>) {
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
}