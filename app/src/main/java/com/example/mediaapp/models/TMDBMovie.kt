package com.example.mediaapp.models

//Data Structure for Search, getPopularMovie, getSimilarMovies, getMovieSuggestions
data class TMDBMovie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)  {
    companion object {
        fun fromMap(map: Map<String, Any?>): TMDBMovie {
            return TMDBMovie(
                map["adult"] as? Boolean ?: false,
                map["backdrop_path"] as? String ?: "",
                map["genre_ids"] as? List<Int> ?: listOf(),
                map["id"] as? Int ?: -1,
                map["media_type"] as? String ?: "",
                map["original_language"] as? String ?: "",
                map["original_title"] as? String ?: "",
                map["overview"] as? String ?: "",
                map["popularity"] as? Double ?: -1.0,
                map["posterPath"] as? String ?: "",
                map["release_date"] as? String ?: "",
                map["title"] as? String ?: "",
                map["video"] as? Boolean ?: false,
                map["vote_average"] as Double ?: -1.0,
                map["vote_count"] as Int ?: -1
            )
        }
    }
}