package com.example.apilist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apilist.R
import com.example.apilist.data.models.RedditPost
import com.example.apilist.ui.util.ServiceLocator
import com.example.apilist.ui.util.getViewModel
import com.example.apilist.ui.adapters.PostsAdapter
import com.example.apilist.ui.fragments.main.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val viewModel by lazy {
        getViewModel {
            MainViewModel(
                ServiceLocator.instance().getRepository()
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun prepareRecyclerView(postList: List<RedditPost>) {
        list.layoutManager = LinearLayoutManager(this.context)
        val adapter = PostsAdapter(Glide.with(this), postList)
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        list.adapter = adapter
    }

    private fun initAdapter() {
        viewModel.allPosts.observe(viewLifecycleOwner, Observer {
            prepareRecyclerView(it)
        })
    }
}
