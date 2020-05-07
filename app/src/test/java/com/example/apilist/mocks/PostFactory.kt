package com.example.apilist.mocks

import com.example.apilist.data.models.RedditPost
import java.util.concurrent.atomic.AtomicInteger

class PostFactory {
    private val counter = AtomicInteger(0)

    //valid post, should be used by the repository
    fun createRedditPost() : RedditPost {
        val id = counter.incrementAndGet()
        return RedditPost(
            title = "title $id",
            thumbnail = "http://fake.fake/image.png",
            url = null
        )
    }

    //Self post, should NOT be used by the repository
    fun createRedditPostSelf() : RedditPost {
        val id = counter.incrementAndGet()
        return RedditPost(
            title = "title $id",
            thumbnail = "self",
            url = null
        )
    }

    //NSFW post, should NOT be used by the repository
    fun createRedditPostNsfw() : RedditPost {
        val id = counter.incrementAndGet()
        return RedditPost(
            title = "title $id",
            thumbnail = "nsfw",
            url = null
        )
    }
}