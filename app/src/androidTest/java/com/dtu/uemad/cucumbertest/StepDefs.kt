package com.example.mediaapp.java.com.dtu.uemad.cucumbertest

import SearchViewModel
import androidx.test.platform.app.InstrumentationRegistry
import com.dtu.uemad.cucumbertest.ActivityScenarioHolder
import com.dtu.uemad.cucumbertest.ComposeRuleHolder
import com.example.mediaapp.MainActivity
import com.example.mediaapp.Screen
import io.cucumber.java.PendingException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When


class StepDefsval (composeRuleHolder: ComposeRuleHolder,
                   val scenarioHolder: ActivityScenarioHolder
) {

        @Given("^I am on the Search page")
        fun i_am_on_the_search_page() {
                // Write code here that turns the phrase above into concrete actions
                scenarioHolder.launch(MainActivity.createIntent(InstrumentationRegistry.getInstrumentation().targetContext, Screen.Search))
        }

        @When("^I type in a movie")
        fun i_type_in_a_movie() {
                // Write code here that turns the phrase above into concrete actions
                throw PendingException()
        }

        @Then("^I am shown a movie of that name")
        fun i_am_shown_a_movie_of_that_name() {
                // Write code here that turns the phrase above into concrete actions
                throw PendingException()
        }
}