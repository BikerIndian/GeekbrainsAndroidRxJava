package net.svishch.android.githubclient.di

import dagger.Component
import net.svishch.android.githubclient.di.modules.*
import net.svishch.android.githubclient.mvp.model.ModelDataProviders
import net.svishch.android.githubclient.mvp.presenter.InfoPresenter
import net.svishch.android.githubclient.mvp.presenter.MainPresenter
import net.svishch.android.githubclient.mvp.presenter.RepoPresenter
import net.svishch.android.githubclient.mvp.presenter.UsersPresenter
import net.svishch.android.githubclient.ui.MainActivity
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
    fun inject(repoPresenter: RepoPresenter)
    fun inject(infoPresenter: InfoPresenter)

    fun inject(modelDataProviders: ModelDataProviders)
}