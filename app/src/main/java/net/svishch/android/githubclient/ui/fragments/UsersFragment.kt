package net.svishch.android.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import net.svishch.android.githubclient.ApiHolder
import net.svishch.android.githubclient.App
import net.svishch.android.githubclient.R
import net.svishch.android.githubclient.mvp.presenter.UsersPresenter
import net.svishch.android.githubclient.mvp.view.UsersView
import net.svishch.android.githubclient.ui.BackButtonListener
import net.svishch.android.githubclient.ui.adapter.UsersRVAdapter
import net.svishch.android.githubclient.ui.image.GlideImageLoader
import net.svishch.android.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter { UsersPresenter(
        AndroidSchedulers.mainThread(),
        RetrofitGithubUsersRepo(ApiHolder().api),
        App.instance.router) }

    var adapter: UsersRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}