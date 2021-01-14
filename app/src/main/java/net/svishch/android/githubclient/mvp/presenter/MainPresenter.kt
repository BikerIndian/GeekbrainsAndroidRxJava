package net.svishch.android.githubclient.mvp.presenter

import moxy.MvpPresenter
import net.svishch.android.githubclient.mvp.view.MainView
import net.svishch.android.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }

}