package net.svishch.android.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.view.*
import net.svishch.android.githubclient.R
import net.svishch.android.githubclient.mvp.presenter.list.IRepoListPresenter
import net.svishch.android.githubclient.mvp.view.list.RepoItemView

class UsersRepoRVAdapter(val presenter: IRepoListPresenter) : RecyclerView.Adapter<UsersRepoRVAdapter.ViewHolderRepo>() {

    inner class ViewHolderRepo(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer,
            RepoItemView {
        override var pos = -1

        override fun setNum(id: String) = with(containerView) {
            repo_num.text = id
        }

        override fun setNameRepo(text: String) = with(containerView) {
            repo_name.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRepo {
        var viewHolder = ViewHolderRepo(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repo, parent, false))
        return viewHolder
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolderRepo, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }
}