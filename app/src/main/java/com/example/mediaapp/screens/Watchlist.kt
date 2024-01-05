package com.example.mediaapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.ui.MovieListLayout
import com.example.mediaapp.ui.TabsAndFilters
import com.example.mediaapp.ui.nav.TopNavBarA
import com.example.mediaapp.viewModels.WatchlistViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistPage(
    navController: NavController,
    drawerState: DrawerState,
    viewModel: WatchlistViewModel = viewModel()) {

    val watchlistMovies = viewModel.watchList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getWatchlistMovies()
    }

    val scope = rememberCoroutineScope()
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
        val movieLayout = watchlistMovies.value?.let { MovieListLayout(it, navController, scope, viewModel) }
        movieLayout?.MovieList()

    }
}

