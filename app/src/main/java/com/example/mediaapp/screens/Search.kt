package com.example.mediaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.ui.Movie
import com.example.mediaapp.ui.MovieListLayout
import com.example.mediaapp.ui.SearchBar
import com.example.mediaapp.ui.TabsAndFilters
import com.example.mediaapp.ui.nav.TopNavBarA

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(navController: NavController, drawerState: DrawerState) {
    Column(
        modifier = Modifier
            .padding(bottom = 75.dp)
            .background(Color(0xFF141218))
    ) {
        TopNavBarA(navController = navController, drawerState = drawerState)
        SearchBar()
        // UI Tabs and Filters
        val customUITabs = TabsAndFilters(
            tabs = listOf(
                stringResource(R.string.all_movies),
                stringResource(R.string.watchlist)
            ),
            filters = listOf(
                TabsAndFilters.FilterOption(
                    stringResource(R.string.genre),
                    listOf(
                        stringResource(R.string.adventure),
                        stringResource(R.string.comedy),
                        stringResource(R.string.horror),
                        stringResource(R.string.action)
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
            Movie(
                stringResource(R.string.mad_max),
                stringResource(R.string.adventure),
                stringResource(R.string.recommended_by_david_and_4_others), painterResource(id = R.drawable.poster_madmax)),
            Movie(
                stringResource(R.string.zohan),
                stringResource(R.string.action),
                stringResource(R.string.recommended_by_jonathan), painterResource(id = R.drawable.poster_zohan)),
            Movie(
                stringResource(R.string.die_hard),
                stringResource(R.string.action),
                stringResource(R.string.recommended_by_mikkel_and_7_others), painterResource(id = R.drawable.poster_diehard)),
            Movie(
                stringResource(R.string.grown_ups_2),
                stringResource(R.string.comedy),
                stringResource(R.string.recommended_by_valde_and_3_others), painterResource(id = R.drawable.poster_grownups2))
        )
        val movieLayout = MovieListLayout(movies)
        movieLayout.MovieList(navController)
    }
}