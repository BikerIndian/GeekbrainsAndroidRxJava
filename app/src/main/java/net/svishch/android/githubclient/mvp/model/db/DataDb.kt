package net.svishch.android.githubclient.mvp.model.db

import io.reactivex.rxjava3.core.Single
import net.svishch.android.githubclient.mvp.model.cache.IGithubRepositoriesCache
import net.svishch.android.githubclient.mvp.model.cache.IGithubUsersCache
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.model.entity.room.Database
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache

// Работа с базой данных
class DataDb( db: Database) {

    var usersCache : IGithubUsersCache = RoomGithubUsersCache(db)
    var repositoriesCache : IGithubRepositoriesCache = RoomGithubRepositoriesCache(db)

    fun getUsers(): Single<List<GithubUser>> = usersCache.getUsers()
    fun getUsersRepositories(user: GithubUser): Single<List<GithubRepository>>? = repositoriesCache.getUsersRepositories(user)

    fun usersUpdate(users: Single<List<GithubUser>>) = usersCache.usersUpdate(users)
    fun repoUpdate(user: GithubUser, repo: Single<List<GithubRepository>>?)= repositoriesCache.repoUpdate(user,repo)
}
