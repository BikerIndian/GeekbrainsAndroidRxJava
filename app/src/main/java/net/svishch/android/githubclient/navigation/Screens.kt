package net.svishch.android.githubclient.navigation


import net.svishch.android.githubclient.ui.fragments.UserFragment
import net.svishch.android.githubclient.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }
    // Фрагмент окна для ввода логина и пароля
    class UserScreen() : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance()
    }
}