package net.svishch.android.githubclient.mvp.view.list

interface RepoItemView : IItemView {
    fun setNum(id: String)
    fun setNameRepo(name: String)
}