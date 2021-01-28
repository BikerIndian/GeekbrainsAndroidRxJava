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


class UserRepoFragment(val user: GithubUser) : MvpAppCompatFragment(), UserRepoView, BackButtonListener {

    val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
                AndroidSchedulers.mainThread(), App.instance.router, ModelDataProviders.newInstance()
        )
    }
    var adapter: UsersRepoRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            View.inflate(context, R.layout.repos_list, null)

    override fun init() {

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