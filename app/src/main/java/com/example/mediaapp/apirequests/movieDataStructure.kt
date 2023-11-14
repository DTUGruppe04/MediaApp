package com.example.mediaapp.apirequests

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
)

data class TMDBMovieResponse(
    val page: Int,
    val results: List<TMDBMovie>,
    val total_pages: Int,
    val total_results: Int
)

//Data Structure for Movie Detail
data class TMDBMovieDetail(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

// Data Structure for getSimilarMovies

data class TMDBMovieReview(
    val id: Int,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

data class Result(
    val author: String,
    val author_details: AuthorDetails,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)

data class AuthorDetails(
    val avatar_path: String,
    val name: String,
    val rating: Double?,
    val username: String
)

// Data structure for getMovieCredits

data class TMDBMovieCredits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)

data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

// Data Structure for getUpcomingMovies and getNowPlayingMovies
data class TMDBUpcomingMovies(
    val dates: Dates,
    val page: Int,
    val results: List<Result2>,
    val total_pages: Int,
    val total_results: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)

data class Result2(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
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
)


/*

// getWhereToWatchMovie()
// This Data Structure isn't done yet.

data class TMDBWhereToWatchMovie(
    val id: Int,
    val results: Results
)

data class Results(
    val AR: AR,
    val AT: AT,
    val AU: AU,
    val BG: BG,
    val BO: BO,
    val BR: BR,
    val BY: BY,
    val BZ: BZ,
    val CA: CA,
    val CH: CH,
    val CL: CL,
    val CO: CO,
    val CR: CR,
    val CV: CV,
    val CY: CY,
    val CZ: CZ,
    val DK: DK,
    val EC: EC,
    val EE: EE,
    val FI: FI,
    val FR: FR,
    val GH: GH,
    val GR: GR,
    val GT: GT,
    val HN: HN,
    val HU: HU,
    val IN: IN,
    val IT: IT,
    val JP: JP,
    val KR: KR,
    val LT: LT,
    val MU: MU,
    val MX: MX,
    val MY: MY,
    val MZ: MZ,
    val NI: NI,
    val NL: NL,
    val NZ: NZ,
    val PE: PE,
    val PH: PH,
    val PL: PL,
    val PT: PT,
    val PY: PY,
    val SE: SE,
    val SI: SI,
    val SK: SK,
    val SM: SM,
    val TH: TH,
    val UA: UA,
    val UG: UG,
    val US: US,
    val VE: VE
)
data class Ad(
    val display_priority: Int,
    val logo_path: String,
    val provider_id: Int,
    val provider_name: String
)

data class Rent(
    val display_priority: Int,
    val logo_path: String,
    val provider_id: Int,
    val provider_name: String
)

data class Flatrate(
    val display_priority: Int,
    val logo_path: String,
    val provider_id: Int,
    val provider_name: String
)

data class Buy(
    val display_priority: Int,
    val logo_path: String,
    val provider_id: Int,
    val provider_name: String
)

data class VE(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

data class US(
    val ads: List<Ad>,
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String,
    val rent: List<Rent>
)

data class UG(
    val buy: List<Buy>,
    val link: String
)

data class UA(
    val buy: List<Buy>,
    val link: String
)
data class TH(
    val buy: List<Buy>,
    val link: String
)

data class SM(
    val flatrate: List<Flatrate>,
    val link: String
)

data class SK(
    val buy: List<Buy>,
    val link: String
)

data class SI(
    val buy: List<Buy>,
    val link: String
)

data class SE(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

data class PY(
    val buy: List<Buy>,
    val link: String
)

data class PT(
    val buy: List<Buy>,
    val link: String
)

data class PL(
    val buy: List<Buy>,
    val link: String
)

data class PH(
    val buy: List<Buy>,
    val link: String
)

data class PE(
    val buy: List<Buy>,
    val link: String
)

data class NZ(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

data class NL(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

data class NI(
    val buy: List<Buy>,
    val link: String
)

data class MZ(
    val buy: List<Buy>,
    val link: String
)

data class MY(
    val buy: List<Buy>,
    val link: String
)

data class MX(
    val buy: List<Buy>,
    val link: String
)

data class MU(
    val buy: List<Buy>,
    val link: String
)

data class LT(
    val buy: List<Buy>,
    val link: String
)

data class KR(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

data class JP(
    val link: String,
    val rent: List<Rent>
)

data class IT(
    val flatrate: List<Flatrate>,
    val link: String
)

data class IN(
    val link: String,
    val rent: List<Rent>
)

data class HU(
    val buy: List<Buy>,
    val link: String
)

data class HN(
    val buy: List<Buy>,
    val link: String
)

data class GT(
    val buy: List<Buy>,
    val link: String
)

data class GR(
    val buy: List<Buy>,
    val link: String
)

data class GH(
    val buy: List<Buy>,
    val link: String
)

data class FR(
    val link: String,
    val rent: List<Rent>
)

data class FI(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

data class EE(
    val buy: List<Buy>,
    val link: String
)

data class EC(
    val buy: List<Buy>,
    val link: String
)

data class DK(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

data class CZ(
    val buy: List<Buy>,
    val link: String
)

data class CY(
    val buy: List<Buy>,
    val link: String
)

data class CV(
    val buy: List<Buy>,
    val link: String
)

data class CR(
    val buy: List<Buy>,
    val link: String
)

data class CO(
    val buy: List<Buy>,
    val link: String
)

data class CL(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

data class CH(
    val ads: List<Ad>,
    val link: String
)

data class CA(
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String
)

data class BZ(
    val buy: List<Buy>,
    val link: String
)

data class BY(
    val buy: List<Buy>,
    val link: String
)

data class BR(
    val link: String,
    val rent: List<Rent>
)

data class BO(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

data class BG(
    val buy: List<Buy>,
    val link: String
)

data class AU(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

data class AT(
    val ads: List<Ad>,
    val link: String
)

data class AR(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)

 */