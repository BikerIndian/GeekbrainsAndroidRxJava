package net.svishch.android.githubclient.mvp.presenter

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import net.svishch.android.githubclient.mvp.model.ModelData
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.model.repo.IGithubUsersRepo
import net.svishch.android.githubclient.mvp.presenter.list.IUserListPresenter
import net.svishch.android.githubclient.mvp.view.UsersView
import net.svishch.android.githubclient.mvp.view.list.UserItemView
import net.svishch.android.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter(private val mainThreadScheduler: Scheduler) : MvpPresenter<UsersView>() {

    @Inject
    lateinit var modelData: IGithubUsersRepo
    @Inject
    lateinit var router: Router


    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]

            user.login?.let { view.setLogin(it) }       // проверка на null так как работат с сетью
            user.avatarUrl?.let { view.loadAvatar(it) }   // проверка на null так как работат с сетью
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        // Выбор пользователя
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.UserRepoScreen(usersListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData() {
        modelData.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn (mainThreadScheduler)
                .subscribe({ users ->
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(users)
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}