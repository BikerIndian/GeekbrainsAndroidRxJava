package net.svishch.android.githubclient.navigation


import net.svishch.android.githubclient.ui.fragments.UserFragment
import net.svishch.android.githubclient.ui.fragments.UserRepoFragment
import net.svishch.android.githubclient.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }
    // Фрагмент окна для ввода логина и пароля
    class UserScreen(login: String) : SupportAppScreen() {
        private val userFragment =  UserFragment(login)
        override fun getFragment() = userFragment
    }

    class UserRepoScreen(urlRepo: String) : SupportAppScreen() {
        private val userRepoFragment =  UserRepoFragment(urlRepo)
        override fun getFragment() = userRepoFragment
    }
}