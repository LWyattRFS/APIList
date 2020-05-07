package com.example.apilist.data.api

import android.util.Log
import com.example.apilist.data.models.ListingResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditApi {
    @GET("/r/{subreddit}/hot.json")
    fun getTopAsync(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int
    ): Deferred<ListingResponse>

    companion object {
        private const val BASE_URL = "https://www.reddit.com/"

        fun create(): RedditApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            //Discussion: the ConverterFactory could be tested using MockWebServer

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(RedditApi::class.java)
        }
    }
}