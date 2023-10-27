package com.example.mediaapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.mediaapp.R
import com.example.mediaapp.ui.MovieListLayout
import com.example.mediaapp.ui.TabsAndFilters

@Composable
fun WatchlistPage() {
    Column {
        // UI Tabs and Filters
        val customUITabs = TabsAndFilters(
            tabs = listOf(
                stringResource(R.string.all),
                stringResource(R.string.watched), stringResource(R.string.not_watched)
            ),
            filters = listOf(
                TabsAndFilters.FilterOption(
                    stringResource(R.string.genre),
                    listOf(
                        stringResource(R.string.adventure),
                        stringResource(R.string.comedy),
                        stringResource(R.string.horror)
                    )
                ),
                TabsAndFilters.FilterOption(
                    stringResource(R.string.year_from),
                    (1960..2023).map { it.toString() }),
                TabsAndFilters.FilterOption(
                    stringResource(R.string.year_to),
                    (1960..2023).map { it.toString() }),
                TabsAndFilters.FilterOption(
                    stringResource(R.string.rating_from),
                    (0..10).map { it.toString() }),
                TabsAndFilters.FilterOption(
                    stringResource(R.string.rating_to),
                    (0..10).map { it.toString() })
            )
        )
        customUITabs.Render()
        // Movie List
        val movies = listOf(
            MovieListLayout.Movie(
                stringResource(R.string.grown_ups_2),
                stringResource(R.string.comedy),
                stringResource(R.string.recommended_by_kevin_and_7_others), painterResource(id = R.drawable.poster_grownups2)),
            MovieListLayout.Movie(
                stringResource(R.string.zohan),
                stringResource(R.string.adventure),
                stringResource(R.string.recommended_by_david_and_4_others), painterResource(id = R.drawable.poster_zohan)),
            MovieListLayout.Movie(
                stringResource(R.string.borat),
                stringResource(R.string.comedy),
                stringResource(R.string.recommended_by_mikkel_and_2_others), painterResource(id = R.drawable.poster_borat)),
            MovieListLayout.Movie(
                stringResource(R.string.step_brothers),
                stringResource(R.string.comedy),
                stringResource(R.string.recommended_by_jonathan), painterResource(id = R.drawable.poster_stepbrothers))
        )
        val movieLayout = MovieListLayout(movies)
        movieLayout.MovieList()
    }
}

