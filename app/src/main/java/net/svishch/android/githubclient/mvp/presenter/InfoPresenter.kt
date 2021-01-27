package net.svishch.android.githubclient.mvp.presenter

import moxy.MvpPresenter
import net.svishch.android.githubclient.mvp.view.InfoView
import ru.terrakok.cicerone.Router

class InfoPresenter(val router: Router) : MvpPresenter<InfoView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}