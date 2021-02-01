package net.svishch.android.githubclient.mvp.presenter

import moxy.MvpPresenter
import net.svishch.android.githubclient.mvp.view.MainView
import net.svishch.android.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router : Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.navigateTo(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }

}