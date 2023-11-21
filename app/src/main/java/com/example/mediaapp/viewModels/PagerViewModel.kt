import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovie
import com.example.mediaapp.repos.popularMoviesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PagerViewModel(): ViewModel() {

    private val apiHandler = APIHandler()
    private val popularRepo = popularMoviesRepo(apiHandler)

    private val _popularMovies = MutableStateFlow<List<TMDBMovie>>(emptyList())
    val popularMovies: StateFlow<List<TMDBMovie>> = _popularMovies.asStateFlow()

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
}