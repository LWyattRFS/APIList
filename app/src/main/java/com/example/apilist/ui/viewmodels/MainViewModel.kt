package com.example.apilist.ui.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.apilist.data.models.RedditPost
import com.example.apilist.data.repositories.RedditPostRepository

class MainViewModel(
    private val repository: RedditPostRepository
) : ViewModel() {
    val allPosts: LiveData<List<RedditPost>> get() = repository.getMutableLiveData()
}
