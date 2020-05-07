package com.example.apilist.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.apilist.R
import com.example.apilist.data.models.RedditPost
import com.example.apilist.ui.viewholders.RedditPostViewHolder

class PostsAdapter (
    private val glide: RequestManager,
    private val postList: List<RedditPost>
) : RecyclerView.Adapter<RedditPostViewHolder>() {
    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        return RedditPostViewHolder.create(parent, glide)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.main_row
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}