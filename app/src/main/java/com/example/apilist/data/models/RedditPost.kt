package com.example.apilist.data.models

import com.google.gson.annotations.SerializedName

//Discussion: Could be annotated for Room integration
data class RedditPost(
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    val url: String?
)

data class ListingResponse(
    val data: ListingData
)

data class ListingData(
    val children: MutableList<RedditChildrenResponse>
)

data class RedditChildrenResponse(
    val data: RedditPost
)