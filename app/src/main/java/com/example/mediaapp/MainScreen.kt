package com.example.mediaapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.mediaapp.ui.nav.BottomNavBar
import com.example.mediaapp.ui.nav.NavigationGraph
import com.example.mediaapp.ui.nav.TopNavBarE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet (
                drawerContainerColor = colorResource(R.color.black_navbar),
                drawerContentColor = colorResource(R.color.black_navbar)

            ) {
                TopNavBarE(navController = navController, drawerState = drawerState)
                Text(
                    stringResource(R.string.menu),
                    color = colorResource(R.color.top_navbar_text_color),
                    modifier = Modifier.padding(16.dp)
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.profilename)) },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = colorResource(R.color.indicator_color_navbar),
                        selectedTextColor = colorResource(R.color.top_navbar_text_color)
                    ),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = "account circle",
                            tint = colorResource(R.color.white_navitem)
                        )
                    },
                    selected = true,
                    onClick = {
                        navController.navigate(Screen.Profile.route)
                        scope.launch {
                            if(drawerState.isOpen) {
                                drawerState.close()
                            }
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.youfollow)) },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = colorResource(R.color.black_navbar),
                        unselectedTextColor = colorResource(R.color.top_navbar_text_color)
                    ),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Group,
                            contentDescription = "group",
                            tint = colorResource(R.color.white_navitem)
                        )
                    },
                    selected = false,
                    badge = { Badge { Text("100+") } },
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.yourfollowers)) },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = colorResource(R.color.black_navbar),
                        unselectedTextColor = colorResource(R.color.top_navbar_text_color)
                    ),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Groups,
                            contentDescription = "group",
                            tint = colorResource(R.color.white_navitem)
                        )
                    },
                    selected = false,
                    badge = { Badge { Text("100+") } },
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.settings)) },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = colorResource(R.color.black_navbar),
                        unselectedTextColor = colorResource(R.color.top_navbar_text_color)
                    ),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "settings",
                            tint = colorResource(R.color.white_navitem)
                        )
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Screen.Settings.route)
                        scope.launch {
                            if(drawerState.isOpen) {
                                drawerState.close()
                            }
                        }
                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.red)
                        ),
                        modifier = Modifier
                            .width(296.dp)
                            .height(40.dp)
                    ) {
                        Text(text = stringResource(R.string.logout))
                    }
                }

            }
        },
        //Need something to handle this in the feature
        gesturesEnabled = false,
        drawerState = drawerState,
        content = {
            Scaffold(
                bottomBar = { BottomNavBar(navController = navController) },
            ) { innerPadding ->
                NavigationGraph(navController = navController, drawerState = drawerState)
                Modifier.padding(innerPadding)
            }
        },
    )
}


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) },
    ) { innerPadding ->
        NavigationGraph(navController = navController)
        Modifier.padding(innerPadding)
    }
}
*/
/*
@Preview
@Composable
fun PreviewMainScreen() {
    val navController = rememberNavController()
    BottomNavigationBar(navController = navController)
    NavBottomGraph(navController = navController)
}
*/