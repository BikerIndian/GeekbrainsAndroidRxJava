package net.svishch.android.githubclient.mvp.presenter

import io.reactivex.rxjava3.schedulers.Timed
import moxy.MvpPresenter
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.model.entity.GithubUsersRepo
import net.svishch.android.githubclient.mvp.presenter.list.IUserListPresenter
import net.svishch.android.githubclient.mvp.view.UsersView
import net.svishch.android.githubclient.mvp.view.list.UserItemView
import net.svishch.android.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        // Выбор пользователя
        usersListPresenter.itemClickListener = { itemView ->

            router.navigateTo(Screens.UserScreen(usersListPresenter.users[itemView.pos].login))
        }
    }

    private fun loadData() {
        val users =  usersRepo.getUsers()
        users.subscribe {usersListPresenter.users.add(it)}
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}