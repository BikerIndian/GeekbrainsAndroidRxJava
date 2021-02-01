package net.svishch.android.githubclient.di

import dagger.Component
import net.svishch.android.githubclient.di.modules.*
import net.svishch.android.githubclient.mvp.presenter.InfoPresenter
import net.svishch.android.githubclient.mvp.presenter.MainPresenter
import net.svishch.android.githubclient.mvp.presenter.UsersPresenter
import net.svishch.android.githubclient.ui.MainActivity
import net.svishch.android.githubclient.ui.fragments.InfoFragment
import net.svishch.android.githubclient.ui.fragments.UserRepoFragment
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
    fun inject(infoPresenter: InfoPresenter)

    // ДЗ - избавиться от зависимостей ниже
    fun inject(usersFragment: UsersFragment)
    fun inject(userRepoFragment: UserRepoFragment)
    fun inject(infoFragment: InfoFragment)
    //fun inject(repositoryFragment: RepositoryFragment)
}