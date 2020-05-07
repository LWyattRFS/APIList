package com.example.apilist.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.apilist.data.api.RedditApi
import com.example.apilist.data.models.RedditPost
import kotlinx.coroutines.*
import retrofit2.HttpException

class RedditPostRepository(
    private val redditApi: RedditApi
) {
    private val mutableLiveData = MutableLiveData<List<RedditPost>>()
    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    fun getMutableLiveData(): MutableLiveData<List<RedditPost>> {
        //Discussion: naive caching
        if (mutableLiveData.value?.size ?: 0 > 0) return mutableLiveData

        //Discussion: coroutines
        coroutineScope.launch {
            val request = redditApi.getTopAsync("pics", 30)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()

                    //Discussion: higher-order functions
                    mutableLiveData.value = response.data.children.filter {
                        it.data.thumbnail != "self" && it.data.thumbnail != "nsfw"
                    }.map {
                        it.data
                    }
                } catch (e: HttpException) {
                    //Discussion: errors, loading could be encapsulated in response
                    TODO()
                } catch (e: Throwable) {
                    TODO()
                }
            }
        }
        return mutableLiveData
    }
}

