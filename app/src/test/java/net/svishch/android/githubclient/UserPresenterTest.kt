package net.svishch.android.githubclient

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import net.svishch.android.githubclient.mvp.model.ModelData
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.mvp.presenter.UsersPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class UserPresenterTest {
    private lateinit var usersPresenter: UsersPresenter

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
        usersPresenter = UsersPresenter(mainThreadScheduler, router, modelData)

    }

    @Test
    fun subscribeActual_Test() {

        val single = object : Single<List<GithubUser>>() {
            override fun subscribeActual(observer: SingleObserver<in List<GithubUser>>?) {
            }
        }

        Mockito.`when`(modelData.getUsers()).thenReturn(single)

        usersPresenter.onFirstViewAttach()
        Mockito.verify(modelData, Mockito.times(1)).getUsers()
    }
}