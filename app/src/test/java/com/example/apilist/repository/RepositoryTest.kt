package com.example.apilist.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.apilist.data.repositories.RedditPostRepository
import com.example.apilist.mocks.FakeRedditApi
import com.example.apilist.mocks.PostFactory
import com.example.apilist.utilities.CoroutineTestRule
import com.example.apilist.utilities.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test

class RepositoryTest {
    @Suppress("unused")
    @get:Rule // used to make all live data calls sync
    val instantExecutor = InstantTaskExecutorRule()
    private val fakeApi = FakeRedditApi()
    private val repository =
        RedditPostRepository(fakeApi)
    private val postFactory = PostFactory()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule =
        CoroutineTestRule()

    /**
     * Given: empty API response
     * When: Repository LiveData is observed
     * Then: Repository LiveData size is 0
     */
    @Test
    fun emptyList() {
        //GIVEN
        val listing = repository.getMutableLiveData()
        //WHEN
        val value = listing.getOrAwaitValue()
        //THEN
        MatcherAssert.assertThat(value.size, `is`(0))
    }

    /**
     * Given: API returns a list of 2 invalid Posts
     * When: Repository LiveData is observed
     * Then: Repository LiveData size is 0
     */
    @Test
    fun invalidItem() {
        //GIVEN
        fakeApi.addPost(postFactory.createRedditPostSelf())
        fakeApi.addPost(postFactory.createRedditPostNsfw())
        val listing = repository.getMutableLiveData()
        //WHEN
        val value = listing.getOrAwaitValue()
        //THEN
        MatcherAssert.assertThat(value.size, `is`(0))
    }

    /**
     * Given: API returns a list of 1 Post
     * When: Repository LiveData is observed
     * Then: Repository LiveData size is 1
     */
    @Test
    fun oneItem() {
        //GIVEN
        val post = postFactory.createRedditPost()
        fakeApi.addPost(post)
        //WHEN
        val listing = repository.getMutableLiveData()
        val value = listing.getOrAwaitValue()
        //THEN
        MatcherAssert.assertThat(value.size, `is`(1))
    }

    /**
     * Given: API returns a list of Posts
     * When: Repository LiveData is observed
     * Then: Repository LiveData is list of API's Posts
     */
    @Test
    fun validList() {
        //GIVEN
        val post = postFactory.createRedditPost()
        fakeApi.addPost(post)
        //WHEN
        val listing = repository.getMutableLiveData()
        val value = listing.getOrAwaitValue()
        //THEN
        MatcherAssert.assertThat(value, `is`(listOf(post)))
    }
}