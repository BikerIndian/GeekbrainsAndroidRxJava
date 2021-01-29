package net.svishch.android.githubclient.mvp.presenter

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import net.svishch.android.githubclient.mvp.model.ModelData
import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.presenter.list.IRepoListPresenter
import net.svishch.android.githubclient.mvp.view.UserRepoView
import net.svishch.android.githubclient.mvp.view.list.RepoItemView
import net.svishch.android.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router

class RepoPresenter(
    private val mainThreadScheduler: Scheduler,
    private val router: Router,
    private val modelData: ModelData,
) : MvpPresenter<UserRepoView>() {
    val repoListPresenter = RepoListPresenter()

    // вызывается когда первый раз будет привязана любая View.
    override fun onFirstViewAttach() {
        viewState.init()
        repoListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.InfoScreen(repoListPresenter.getUser(itemView.pos)))
        }
    }

    fun loadData(user: GithubUser) {

        modelData.getUsersRepositories(user)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(mainThreadScheduler)
            ?.subscribe({ repositories ->
                repoListPresenter.update(repositories)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    class RepoListPresenter : IRepoListPresenter {
        private val repository = mutableListOf<GithubRepository>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun bindView(view: RepoItemView) {
            val repo = repository[view.pos]
            view.setNum(view.pos.toString())
            repo.name?.let { view.setNameRepo(it) }

        }

        // Колличество строчек
        override fun getCount() = repository.size

        fun update(repositories: List<GithubRepository>?) {
            repository.clear()
            repositories?.let { repository.addAll(it) }
        }

        fun getUser(pos: Int): GithubRepository {
            return repository[pos]
        }

    }

}


