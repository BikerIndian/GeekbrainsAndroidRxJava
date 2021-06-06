package net.svishch.android.githubclient.navigation


import net.svishch.android.githubclient.mvp.model.entity.GithubRepository
import net.svishch.android.githubclient.mvp.model.entity.GithubUser
import net.svishch.android.githubclient.ui.fragments.InfoFragment
import net.svishch.android.githubclient.ui.fragments.UserRepoFragment
import net.svishch.android.githubclient.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserRepoScreen(val user: GithubUser) : SupportAppScreen() {
      override fun getFragment() = UserRepoFragment.newInstance(user)
    }


    class InfoScreen(val user: GithubRepository) : SupportAppScreen() {
        override fun getFragment() = InfoFragment.newInstance(user)
    }
}