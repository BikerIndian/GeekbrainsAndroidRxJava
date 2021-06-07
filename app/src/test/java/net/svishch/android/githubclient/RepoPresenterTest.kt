package net.svishch.android.githubclient

import io.reactivex.rxjava3.core.Scheduler
import net.svishch.android.githubclient.mvp.model.ModelData
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.presenter.RepoPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class RepoPresenterTest {
    private lateinit var repoPresenter: RepoPresenter

    @Mock
    private lateinit var mainThreadScheduler: Scheduler

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var modelData: ModelData

    @Mock
    private lateinit var user: GithubUser


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repoPresenter = RepoPresenter(mainThreadScheduler, router, modelData)
    }

    @Test
    fun searchGithub_Test() {

        repoPresenter.loadData(user)
        Mockito.verify(modelData, Mockito.times(1)).getUsersRepositories(user)
    }
}