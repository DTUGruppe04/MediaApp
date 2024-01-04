import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.RecommendationEngine
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.Recommend
import com.example.mediaapp.models.TMDBMovie
import com.example.mediaapp.repos.popularMoviesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {

    private val apiHandler = APIHandler()
    private val popularRepo = popularMoviesRepo(apiHandler)
    private val algorithm = RecommendationEngine()

    //States for pager
    private val _popularMovies = MutableStateFlow<List<TMDBMovie>>(emptyList())
    val popularMovies: StateFlow<List<TMDBMovie>> = _popularMovies.asStateFlow()

    //States for recommendation algorithm
    private val _recommendedMovies = MutableStateFlow<List<Recommend>>(emptyList())
    val recommendedMovies: StateFlow<List<Recommend>> = _recommendedMovies.asStateFlow()

    private val _recommendedState = MutableStateFlow<Boolean?>(null)
    val recommendedState: StateFlow<Boolean?> = _recommendedState

    //Error States for Homepage
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchPopularMovies() {
        viewModelScope.launch {
            popularRepo.getPopularMovies("week").also { result ->
                result.onSuccess { movies ->
                    _popularMovies.value = movies
                }.onFailure { throwable ->
                    _error.value = throwable.localizedMessage ?: "Unknown Error"
                }
            }
        }
    }

    fun fetchRecommendedMovies() {
        viewModelScope.launch {
            _recommendedMovies.value = algorithm.getRecommendMovies()
        }
    }

    fun fetchRecommendedState() {
        viewModelScope.launch {
            _recommendedState.value = algorithm.isUserValid()
        }
    }

}