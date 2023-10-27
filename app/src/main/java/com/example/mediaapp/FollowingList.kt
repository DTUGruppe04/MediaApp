package com.example.mediaapp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun FollowingListPage() {
    Column {
        // UI Tabs and Filters
        val customUITabs = TabsAndFilters(
            tabs = listOf(stringResource(R.string.recommended),
                stringResource(R.string.last_rated)
            ),
            filters = listOf(
                TabsAndFilters.FilterOption(stringResource(R.string.genre) , listOf(stringResource(R.string.adventure), stringResource(R.string.comedy), stringResource(R.string.horror), stringResource(R.string.action))),
                TabsAndFilters.FilterOption(stringResource(R.string.year_from), (1960..2023).map { it.toString() }),
                TabsAndFilters.FilterOption(stringResource(R.string.year_to), (1960..2023).map { it.toString() }),
                TabsAndFilters.FilterOption(stringResource(R.string.rating_from), (0..10).map { it.toString() }),
                TabsAndFilters.FilterOption(stringResource(R.string.rating_to), (0..10).map { it.toString() })
            )
        )
        customUITabs.Render()
        // Movie List
        val movies = listOf(
            MovieListLayout.Movie(
                stringResource(R.string.grown_ups_2),
                stringResource(R.string.action),
                stringResource(R.string.recommended_by_david_and_4_others), painterResource(id = R.drawable.poster_avatar)),
            MovieListLayout.Movie(
                stringResource(R.string.zohan),
                stringResource(R.string.action),
                stringResource(R.string.recommended_by_jonathan), painterResource(id = R.drawable.poster_madmax)),
            MovieListLayout.Movie(
                stringResource(R.string.borat),
                stringResource(R.string.action),
                stringResource(R.string.recommended_by_mikkel_and_7_others), painterResource(id = R.drawable.poster_diehard)),
            MovieListLayout.Movie(
                stringResource(R.string.step_brothers),
                stringResource(R.string.action),
                stringResource(R.string.recommended_by_valde_and_3_others), painterResource(id = R.drawable.poster_american_sniper))
        )
        val movieLayout = MovieListLayout(movies)
        movieLayout.MovieList()
    }
}