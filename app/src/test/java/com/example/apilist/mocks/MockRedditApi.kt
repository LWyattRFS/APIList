package com.example.apilist.mocks

import com.example.apilist.data.api.RedditApi
import com.example.apilist.data.models.ListingData
import com.example.apilist.data.models.ListingResponse
import com.example.apilist.data.models.RedditChildrenResponse
import com.example.apilist.data.models.RedditPost
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class FakeRedditApi : RedditApi {
    private val model: ListingResponse = ListingResponse(ListingData((ArrayList())))

    fun addPost(post: RedditPost) {
        model.data.children.add(RedditChildrenResponse(post))
    }

    override fun getTopAsync(subreddit: String, limit: Int): Deferred<ListingResponse> {
        val response = ListingResponse(
            ListingData(children = model.data.children)
        )
        return GlobalScope.async {
            response
        }
    }
}