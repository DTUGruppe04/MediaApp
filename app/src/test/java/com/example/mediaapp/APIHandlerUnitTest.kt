package com.example.mediaapp

import androidx.annotation.DisplayContext
import org.junit.Test
import org.junit.Assert.*
import com.example.mediaapp.apirequests.APIHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class APIHandlerUnitTest {
    private val api = APIHandler()
    @Test
    //Testing that the API Call GetPopularMovies does not return an empty result
    fun testGetPopularMoviesIsNotEmpty() {
        runBlocking {
            delay(1000)
            val popularMovies = api.getPopularMovies("week")
            if(popularMovies != null) {
                assertFalse(popularMovies.results.isEmpty())
                assertEquals(1, popularMovies.page)
                assertEquals(1000, popularMovies.total_pages)
                assertEquals(20000, popularMovies.total_results)
            }
        }
    }

    @Test
    //Testing that we're getting the expected data from Search for Oppenheimer
    fun testSearchForMovie() {
        runBlocking {
            delay(1000)
            val searchResults = api.searchForMovie("Oppenheimer")
            if(searchResults != null) {
                assertEquals("Oppenheimer", searchResults.results[0].title)
                assertEquals("2023-07-19", searchResults.results[0].release_date)
                assertEquals("en", searchResults.results[0].original_language)
                assertEquals(872585, searchResults.results[0].id)
                assertEquals(1, searchResults.page)
                assertEquals(2, searchResults.total_pages)
                assertEquals(22, searchResults.total_results)
            }
        }
    }

    @Test
    //Testing that we're getting the expected data from Oppenheimer
    fun testGetMovieDetail() {
        runBlocking {
            delay(1000)
            val movieDetail = api.getMovieDetail("872585")
            if(movieDetail != null) {
                assertEquals(false, movieDetail.adult)
                assertEquals(100000000, movieDetail.budget)
                assertEquals("Released", movieDetail.status)
                assertEquals("Oppenheimer", movieDetail.title)
                assertEquals("tt15398776", movieDetail.imdb_id)
            }
        }
    }

    @Test
    fun testGetMovieSuggestions() {
        runBlocking {
            delay(1000)
            val response = api.getMovieSuggestions("872585")
            if(response != null && response.total_results > 0) {
                assertEquals(2, response.total_pages)
                assertEquals(40, response.total_results)
            }
        }
    }
}