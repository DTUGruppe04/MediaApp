import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovie
import com.example.mediaapp.repos.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel() : ViewModel() {

    private val apiHandler = APIHandler()
    private val searchRepository = SearchRepository(apiHandler)

    private val _searchQuery = MutableStateFlow("")

    private val _searchResults = MutableStateFlow<List<TMDBMovie>>(emptyList())
    val searchResults: StateFlow<List<TMDBMovie>> = _searchResults

    private val _isSearchActive = MutableStateFlow(false)
    val isSearchActive: StateFlow<Boolean> = _isSearchActive

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun setSearchQuery(query: String) {
        if (query.isNotEmpty()) {
            _searchQuery.value = query
            _isSearchActive.value = true
            performSearch()
        } else {
            _isSearchActive.value = false
            resetSearch()
        }
    }

    fun performSearch() {
        val query = _searchQuery.value
        if (query.isNotEmpty()) {
            _isSearchActive.value = true
            viewModelScope.launch {
                searchRepository.searchMovies(query).also { result ->
                    _isSearchActive.value = false
                    result.onSuccess { movies ->
                        _searchResults.value = movies
                    }.onFailure { throwable ->
                        _error.value = throwable.localizedMessage ?: "Unknown Error"
                    }
                }
            }
        }
    }

    fun resetSearch() {
        _searchQuery.value = ""
        _searchResults.value = emptyList()
        _error.value = null
    }
}

