package net.svishch.android.githubclient.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.login.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import net.svishch.android.githubclient.App
import net.svishch.android.githubclient.R
import net.svishch.android.githubclient.mvp.presenter.UserPresenter
import net.svishch.android.githubclient.mvp.view.UserView
import net.svishch.android.githubclient.ui.BackButtonListener


class UserFragment(_login: String) : MvpAppCompatFragment(), UserView, BackButtonListener {
    val login: String = _login
    val presenter: UserPresenter by moxyPresenter { UserPresenter(App.instance.router) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            View.inflate(context, R.layout.login, null)

    override fun init() {

        val textView = (context as Activity).findViewById<View>(R.id.edit_user) as EditText
        textView.setText(login)
        button_login.setOnClickListener{
            presenter.btnOnClick(edit_user.text, edit_password.text)
        }
    }
    override fun backPressed() = presenter.backPressed()
}