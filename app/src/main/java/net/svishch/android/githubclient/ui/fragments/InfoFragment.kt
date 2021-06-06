package net.svishch.android.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_info_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import net.svishch.android.githubclient.App
import net.svishch.android.githubclient.R
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.presenter.InfoPresenter
import net.svishch.android.githubclient.mvp.view.InfoView
import net.svishch.android.githubclient.ui.BackButtonListener


class InfoFragment() : MvpAppCompatFragment(), InfoView,
    BackButtonListener {

    companion object {
        private const val USER_ARG = "userRepository"

        fun newInstance(user: GithubRepository) = InfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    val presenter: InfoPresenter by moxyPresenter {
        InfoPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        View.inflate(context, R.layout.fragment_info_user, null)

    override fun init() {
        val user = arguments?.getParcelable<GithubRepository>(USER_ARG) as GithubRepository

        setId(user.id)
        setName(user.name.toString())
        setForks(user.forksCount.toString())
    }

    override fun setId(text: String) {
        info_id.text = text
    }

    override fun setName(text: String) {
        info_name.text = text
    }

    override fun setForks(text: String) {
        info_forks.text = text
    }

    override fun backPressed() = presenter.backPressed()
}