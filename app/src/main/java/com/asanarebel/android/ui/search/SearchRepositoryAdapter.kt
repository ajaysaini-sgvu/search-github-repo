package com.asanarebel.android.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asanarebel.android.R
import com.asanarebel.android.data.model.search.RepoItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SearchRepositoryAdapter(private val repositoriesList: List<RepoItem>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<SearchRepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(repositoriesList[position])
    }

    override fun getItemCount() = repositoriesList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(repoItem: RepoItem) {
            val textViewName = itemView.findViewById<TextView>(R.id.name_text) as TextView
            val textViewDescription = itemView.findViewById<TextView>(R.id.description_text) as TextView
            val imageViewAvatar = itemView.findViewById<ImageView>(R.id.avatar_image) as ImageView

            textViewName.text = "${repoItem.name} • ${repoItem.forksCount}"
            textViewDescription.text = repoItem.description

            Glide.with(itemView.context).load(repoItem.owner?.avatarUrl)
                    .apply(RequestOptions().placeholder(R.drawable.ic_avatar_40dp).error(R.drawable.ic_avatar_40dp))
                    .into(imageViewAvatar)

            itemView.setOnClickListener {
                onItemClickListener.onClick(repoItem)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(repo: RepoItem)
    }

}