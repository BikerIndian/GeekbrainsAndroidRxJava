package net.svishch.android.githubclient.mvp.presenter

import android.text.Editable
import moxy.MvpPresenter
import net.svishch.android.githubclient.mvp.view.UserView
import ru.terrakok.cicerone.Router

class UserPresenter( val router: Router) : MvpPresenter<UserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }
    fun btnOnClick(login: Editable, pass: Editable){

    }

    fun backPressed(): Boolean {
        return true
    }
}