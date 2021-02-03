package net.svishch.android.githubclient.mvp.presenter

import moxy.MvpPresenter
import net.svishch.android.githubclient.mvp.view.InfoView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class InfoPresenter() : MvpPresenter<InfoView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}