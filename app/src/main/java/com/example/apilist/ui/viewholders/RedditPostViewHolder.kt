package com.example.apilist.ui.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.apilist.R
import com.example.apilist.data.models.RedditPost
import com.example.apilist.ui.fragments.DetailFragment

class RedditPostViewHolder(
    view: View,
    private val glide: RequestManager
) : RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.title)
    private val thumbnail : ImageView = view.findViewById(R.id.thumbnail)
    private var post : RedditPost? = null

    init {
        view.setOnClickListener {
            val bundle = bundleOf(
                DetailFragment.KEY_URL to post?.url,
                DetailFragment.KEY_TITLE to post?.title)
            Navigation.findNavController(view).navigate(R.id.action_main_screen_to_detail, bundle)
        }
    }

    fun bind(post: RedditPost?) {
        this.post = post
        title.text = post?.title
        if (post?.thumbnail?.startsWith("http") == true) {
            thumbnail.visibility = View.VISIBLE
            glide.load(post.thumbnail)
                .centerCrop()
                .into(thumbnail)
        } else {
            thumbnail.visibility = View.GONE
            glide.clear(thumbnail)
        }
    }

    companion object {
        fun create(parent: ViewGroup, glide: RequestManager): RedditPostViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.main_row, parent, false)
            return RedditPostViewHolder(view, glide)
        }
    }
}