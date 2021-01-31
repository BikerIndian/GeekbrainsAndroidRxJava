package net.svishch.android.githubclient.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.svishch.android.githubclient.App
import net.svishch.android.githubclient.mvp.model.entity.room.Database
import net.svishch.android.githubclient.mvp.model.cache.IGithubRepositoriesCache
import net.svishch.android.githubclient.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache

import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }
}