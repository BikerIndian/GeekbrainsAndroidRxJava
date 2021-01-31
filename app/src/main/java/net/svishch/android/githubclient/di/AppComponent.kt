package net.svishch.android.githubclient.di

import dagger.Component
import net.svishch.android.githubclient.di.modules.*
import net.svishch.android.githubclient.mvp.presenter.MainPresenter
import net.svishch.android.githubclient.mvp.presenter.UsersPresenter
import net.svishch.android.githubclient.ui.MainActivity
import net.svishch.android.githubclient.ui.fragments.UserFragment
import net.svishch.android.githubclient.ui.fragments.UsersFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)

    // ДЗ - избавиться от зависимостей ниже
    fun inject(usersFragment: UsersFragment)
    fun inject(userFragment: UserFragment)
    //fun inject(repositoryFragment: RepositoryFragment)
}