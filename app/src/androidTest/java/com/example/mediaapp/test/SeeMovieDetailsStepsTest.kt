package com.example.mediaapp.test

import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovieDetail
import com.example.mediaapp.models.TMDBMovieResponse
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlinx.coroutines.runBlocking

class SeeMovieDetailsStepsTest {

    val api = APIHandler()

    private lateinit var movies: TMDBMovieResponse
    private lateinit var movieDetails: TMDBMovieDetail
    @Given("I open the search page")
    fun i_open_the_search_page( ) {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("I search for Oppenheimer")
    fun i_search_for() {
        // Write code here that turns the phrase above into concrete actions
        runBlocking {
            movies = api.searchForMovie("Oppenheimer")!!
        }
        assert(movies.results[0].title.equals("Oppenheimer"))
    }

    @When("I click on the movie")
    fun i_click_on_the_movie( ) {
        // Write code here that turns the phrase above into concrete actions
        runBlocking {
            movieDetails = api.getMovieDetail(movies.results[0].id.toString())!!
        }
    }

    @Then("I see the movie details")
    fun i_see_the_movie_details( ) {
        // Write code here that turns the phrase above into concrete actions
        assert(movieDetails.title.equals("Oppenheimer"))
    }
}