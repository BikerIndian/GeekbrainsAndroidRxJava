package net.svishch.android.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.repos_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import net.svishch.android.githubclient.App
import net.svishch.android.githubclient.R
import net.svishch.android.githubclient.mvp.model.ModelDataProviders
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.presenter.RepoPresenter
import net.svishch.android.githubclient.mvp.view.UserRepoView
import net.svishch.android.githubclient.ui.BackButtonListener
import net.svishch.android.githubclient.ui.adapter.UsersRepoRVAdapter


class UserRepoFragment() : MvpAppCompatFragment(), UserRepoView, BackButtonListener {

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserRepoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
            AndroidSchedulers.mainThread(), ModelDataProviders.newInstance()
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: UsersRepoRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = View.inflate(context, R.layout.repos_list, null)

    override fun init() {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser

        presenter.loadData(user)

        repos_name_recycle.layoutManager = LinearLayoutManager(context)
        adapter = UsersRepoRVAdapter(presenter.repoListPresenter)
        repos_name_recycle.adapter = adapter

    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}