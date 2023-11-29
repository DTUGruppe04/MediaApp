
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovieResponse
import io.cucumber.java.PendingException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlinx.coroutines.runBlocking

class StepDefsTest {

    val api = APIHandler()

    private lateinit var movies: TMDBMovieResponse
     @Given("I am on the Search page")
     fun i_am_on_the_Search_page(){
         // Write code here that turns the phrase above into concrete actions
     }
    @When("^I enter \"([^\"]*)\" in the search field$")
    fun i_enter_in_the_search_field(arg1: String){
        // Write code here that turns the phrase above into concrete actions
        runBlocking {
            movies = api.searchForMovie("Oppenheimer")!!
        }
    }
    @Then("^I am shown a movie of that name$")
    fun i_am_shown_a_movie_of_that_name(){
        // Write code here that turns the phrase above into concrete actions
        assert(movies.results[0].title.equals("Oppenheimer"))
    }
 }