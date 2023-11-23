package com.example.mediaapp.screens

import SearchViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.ui.SearchBar
import com.example.mediaapp.ui.TabsAndFilters
import com.example.mediaapp.ui.nav.TopNavBarA
import com.example.mediaapp.ui.theme.MediaAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mediaapp.ui.SearchQueryLayout


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(viewModel: SearchViewModel = viewModel(), navController: NavController, drawerState: DrawerState) {

    val searchResults by viewModel.searchResults.collectAsState()
    val isSearchActive by viewModel.isSearchActive.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    MediaAppTheme {
        Column(
            modifier = Modifier
                .padding(bottom = 75.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            TopNavBarA(navController = navController, drawerState = drawerState)
            SearchBar(
                query = searchQuery,
                onSearch = { query ->
                    viewModel.setSearchQuery(query)
                    viewModel.performSearch(query) }
            )

            if (isSearchActive) {
                SearchQueryLayout.SearchQueryList(movies = searchResults, navController = navController)
            }
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
        }
    }
}
