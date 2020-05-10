package com.example.apilist.dagger

import com.example.apilist.data.api.RedditApi
import com.example.apilist.data.repositories.RedditPostRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesRedditPostRepository(): RedditPostRepository {
        return RedditPostRepository(RedditApi.create())
    }
}