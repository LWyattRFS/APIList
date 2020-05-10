package com.example.apilist.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.apilist.dagger.DaggerRepositoryComponent
import com.example.apilist.data.models.RedditPost
import com.example.apilist.data.repositories.RedditPostRepository
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject lateinit var repository: RedditPostRepository

    val allPosts: LiveData<List<RedditPost>> get() = repository.getMutableLiveData()

    init {
        DaggerRepositoryComponent.create().inject(this)
    }
}
