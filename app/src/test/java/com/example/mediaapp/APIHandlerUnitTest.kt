package com.example.mediaapp

import androidx.annotation.DisplayContext
import org.junit.Test
import org.junit.Assert.*
import com.example.mediaapp.backend.apirequests.APIHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class APIHandlerUnitTest {
    private val api = APIHandler()
    private val oppenheimer = "872585"
    @Test
    fun testGetPopularMovies() {
        runBlocking {
            delay(1000)
            val response = api.getPopularMovies("week")
            if(response != null) {
                assertFalse(response.results.isEmpty())
                assertEquals(1, response.page)
                assertEquals(1000, response.total_pages)
                assertEquals(20000, response.total_results)
            }
        }
    }

    @Test
    fun testSearchForMovies() {
        runBlocking {
            delay(1000)
            val response = api.searchForMovie(oppenheimer)
            if(response != null) {
                assertEquals("Oppenheimer", response.results[0].title)
                assertEquals("2023-07-19", response.results[0].release_date)
                assertEquals("en", response.results[0].original_language)
                assertEquals(872585, response.results[0].id)
                assertEquals(1, response.page)
                assertEquals(2, response.total_pages)
                assertEquals(22, response.total_results)
            }
        }
    }

    @Test
    //Testing that we're getting the expected data from Oppenheimer
    fun testGetMovieDetail() {
        runBlocking {
            delay(1000)
            val response = api.getMovieDetail(oppenheimer)
            if(response != null) {
                assertEquals(false, response.adult)
                assertEquals(100000000, response.budget)
                assertEquals("Released", response.status)
                assertEquals("Oppenheimer", response.title)
                assertEquals("tt15398776", response.imdb_id)
            }
        }
    }
    @Test
    fun testGetSimilarMovies() {
        runBlocking {
            delay(1000)
            val response = api.getSimilarMovies(oppenheimer)
            if(response != null) {
                assertEquals(1, response.page)
                assertEquals(11195, response.total_pages)
                assertEquals(223891, response.total_results)
            }
        }
    }

    @Test
    fun testGetMovieReviews() {
        runBlocking {
            delay(1000)
            val response = api.getMovieReviews(oppenheimer)
            if(response != null) {
                assertEquals(1, response.page)
                assertEquals(1, response.total_pages)
                assertEquals(16, response.total_results)
            }
        }
    }

    @Test
    fun testGetMovieCredits() {
        runBlocking {
            delay(1000)
            val response = api.getMovieCredits(oppenheimer)
            if(response != null) {
                assertEquals(oppenheimer, response.id)
                assertEquals(1, response.cast.size)
            }
        }
    }
}