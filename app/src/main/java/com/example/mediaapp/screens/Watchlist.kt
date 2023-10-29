package com.example.mediaapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.ui.MovieListLayout
import com.example.mediaapp.ui.TabsAndFilters
import com.example.mediaapp.ui.nav.TopNavBarA

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistPage(navController: NavController, drawerState: DrawerState) {
    Column (
        //Adding this padding for the bottom navigation bar
        modifier = Modifier.padding(bottom = 70.dp)
    ) {
        TopNavBarA(navController = navController, drawerState = drawerState)
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
        movieLayout.MovieList(navController = navController)
    }
}

