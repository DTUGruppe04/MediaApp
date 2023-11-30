package com.example.mediaapp.test

import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovieResponse
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlinx.coroutines.runBlocking

class HighlightPageStepsTest {

    val api = APIHandler()

    private lateinit var highlightMovies: TMDBMovieResponse

    @Given("I am on the highlight page")
    fun iAmOnTheHighlightPage() {
    }

    @When("I look at the top of the page")
    fun iLookAtTheTopOfThePage() {
        runBlocking {
            highlightMovies = api.getPopularMovies("week")!!
        }
    }

    @Then("I should not see an empty list")
    fun iShouldNotSeeAnEmptyList() {
        assert(highlightMovies.results.isNotEmpty())
    }
}