package net.svishch.android.githubclient.mvp.view.list

import net.svishch.android.githubclient.mvp.view.list.IItemView

interface UserItemView : IItemView {
    fun setLogin(text: String)
}