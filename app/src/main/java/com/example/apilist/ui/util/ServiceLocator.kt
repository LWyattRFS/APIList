package com.example.apilist.ui.util

import androidx.annotation.VisibleForTesting
import com.example.apilist.data.api.RedditApi
import com.example.apilist.data.repositories.RedditPostRepository

interface ServiceLocator {
    companion object {
        private val LOCK = Any()
        private var instance: ServiceLocator? = null
        fun instance(): ServiceLocator {
            synchronized(LOCK) {
                if (instance == null) {
                    instance =
                        DefaultServiceLocator()
                }
                return instance!!
            }
        }

        //Discussion: Allows instrumented tests to replace the default implementations.
        @VisibleForTesting
        fun swap(locator: ServiceLocator) {
            instance = locator
        }
    }

    fun getRepository(): RedditPostRepository

    fun getRedditApi(): RedditApi
}

open class DefaultServiceLocator : ServiceLocator {
    private val api by lazy {
        RedditApi.create()
    }

    override fun getRepository(): RedditPostRepository {
        return RedditPostRepository(
            redditApi = getRedditApi()
        )
    }

    override fun getRedditApi(): RedditApi {
        return api
    }
}