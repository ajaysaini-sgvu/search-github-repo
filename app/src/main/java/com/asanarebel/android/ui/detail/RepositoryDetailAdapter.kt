package com.asanarebel.android.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asanarebel.android.R
import com.asanarebel.android.data.model.subscribers.SubscriberResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RepositoryDetailAdapter(private val subscribers: List<SubscriberResponse>) : RecyclerView.Adapter<RepositoryDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(subscribers[position])
    }

    override fun getItemCount() = subscribers.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(subscriber: SubscriberResponse) {
            val textViewName = itemView.findViewById<TextView>(R.id.name_text) as TextView
            val imageViewAvatar = itemView.findViewById<ImageView>(R.id.avatar_image) as ImageView

            itemView.findViewById<TextView>(R.id.description_text).visibility = View.GONE

            textViewName.text = subscriber.login

            Glide.with(itemView.context).load(subscriber.avatarUrl)
                    .apply(RequestOptions().placeholder(R.drawable.ic_avatar_40dp).error(R.drawable.ic_avatar_40dp))
                    .into(imageViewAvatar)

        }
    }

}