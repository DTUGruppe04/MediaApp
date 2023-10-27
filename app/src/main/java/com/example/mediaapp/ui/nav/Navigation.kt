package com.example.mediaapp.ui.nav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediaapp.BottomNavItem
import com.example.mediaapp.R

@Composable
fun BottomNavBar(navController: NavController, modifier : Modifier = Modifier) {
    val bottomNavItems = listOf(
        BottomNavItem(
            name = stringResource(R.string.home),
            route = "home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            name = stringResource(R.string.following),
            route = "following",
            selectedIcon = Icons.Filled.Subscriptions,
            unselectedIcon = Icons.Outlined.Subscriptions
        ),
        BottomNavItem(
            name = stringResource(R.string.watchlist),
            route = "watchlist",
            selectedIcon = Icons.Filled.FormatListBulleted,
            unselectedIcon = Icons.Outlined.FormatListBulleted
        ),
        BottomNavItem(
            name = stringResource(R.string.search),
            route = "search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        ),
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val contentColor = colorResource(R.color.white_navitem)
    val containerColor = colorResource(R.color.black_navbar)
    val indicatorColor = colorResource(R.color.indicator_color_navbar)

    NavigationBar(
        containerColor = containerColor,
        tonalElevation = 0.dp,
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = contentColor,
                    unselectedIconColor = contentColor,
                    indicatorColor = indicatorColor
                ),
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route)
                },
                label = {
                    Text(text = item.name, color = contentColor)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.name
                    )
                }
            )
        }
    }
}


/**
 * TopNavBarA
 *
 * This function has a Hamburger to the left followed by a name. See Figma for a more precise
 * example or preview the function
 *
 * @param navController navigation controller
 * @param modifier optional
 *
 * @return Returns a Top Navigation Bar type A.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBarA(navController: NavController, modifier : Modifier = Modifier) {
    val containerColor = colorResource(R.color.top_navbar_container_color)
    val contentColor = colorResource(R.color.top_navbar_text_color)
    val iconColor = colorResource(R.color.top_navbar_icon_color)

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = contentColor,
            navigationIconContentColor = iconColor,
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "menu"
                )
            }
        },
        title = {
            Text(stringResource(R.string.mediaapp))
        }
    )
}

/**
 * TopNavBarB
 *
 * This function has a LeftArrow icon to the left, a centered name, and a trailing-icon to the right. See Figma for a more precise
 * example or preview the function
 *
 * @param navController navigation controller
 * @param modifier optional
 * @return Returns a Top Navigation Bar of type B.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBarB(navController: NavController, modifier : Modifier = Modifier) {
    val containerColor = colorResource(R.color.top_navbar_container_color)
    val contentColor = colorResource(R.color.top_navbar_text_color)
    val iconColor = colorResource(R.color.top_navbar_icon_color)

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = contentColor,
            navigationIconContentColor = iconColor,
            actionIconContentColor = iconColor,
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "menu"
                )
            }
        },
        title = {
            Text(stringResource(R.string.mediaapp))
        },
    )
}


/**
 * TopNavBarC
 *
 * This function has a Hamburger to the left, a name, and an edit button to the right. See Figma for a more precise
 * example or preview the function
 *
 * @param navController navigation controller
 * @param modifier optional
 * @return Returns a Top Navigation Bar of type C.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBarC(navController: NavController, modifier : Modifier = Modifier) {
    val containerColor = colorResource(R.color.top_navbar_container_color)
    val contentColor = colorResource(R.color.top_navbar_text_color)
    val iconColor = colorResource(R.color.top_navbar_icon_color)
    val bottomColor = colorResource(R.color.indicator_color_navbar)

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = contentColor,
            navigationIconContentColor = iconColor,
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "menu"
                )
            }
        },
        title = {
            Text("Media App")
        },
        actions = {
            Button(
                onClick = { /*TODO*/  },
                colors = ButtonDefaults.buttonColors(
                    containerColor = bottomColor
                ),
            ) {
                Text(stringResource(R.string.mediaapp))
            }
        }
    )
}

/**
 * TopNavBarD
 *
 * This function has a left_arrow icon to the left and a name centerted with more icon to the left. See Figma for a more precise
 * example or preview the function
 *
 * @param navController navigation controller
 * @param modifier optional
 * @return Returns a Top Navigation Bar of type D.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBarD(navController: NavController, modifier : Modifier = Modifier) {
    val containerColor = colorResource(R.color.top_navbar_container_color)
    val contentColor = colorResource(R.color.top_navbar_text_color)
    val iconColor = colorResource(R.color.top_navbar_icon_color)

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = contentColor,
            navigationIconContentColor = iconColor,
            actionIconContentColor = iconColor
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "arrow back"
                )
            }
        },
        title = {
            Text(stringResource(R.string.mediaapp))
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "more vert"
                )
            }
        }
    )
}

/**
 * TopNavBarE
 *
 * This function has a axe icon to the left and a name. See Figma for a more precise
 * example or preview the function
 *
 * @param navController navigation controller
 * @param modifier optional
 * @return Returns a Top Navigation Bar of type D.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBarE(navController: NavController, modifier : Modifier = Modifier) {
    val containerColor = colorResource(R.color.top_navbar_container_color)
    val contentColor = colorResource(R.color.top_navbar_text_color)
    val iconColor = colorResource(R.color.top_navbar_icon_color)

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = contentColor,
            navigationIconContentColor = iconColor,
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "arrow back",
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                )
            }
        },
        title = {
            Text(stringResource(R.string.mediaapp))
        }
    )
}

/**
 * TopNavBarF
 *
 * This function has a centered name. See Figma for a more precise
 * example or preview the function
 *
 * @param navController navigation controller
 * @param modifier optional
 * @return Returns a Top Navigation Bar of type F.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBarF(navController: NavController, modifier : Modifier = Modifier) {
    val containerColor = colorResource(R.color.top_navbar_container_color)

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = containerColor,
        ),
        title = {
            Text(stringResource(R.string.mediaapp))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(navController: NavController, modifier : Modifier = Modifier) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet (
                drawerContainerColor = colorResource(R.color.black_navbar),
                drawerContentColor = colorResource(R.color.black_navbar)

            ) {
                TopNavBarE(navController = navController)
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
                    onClick = { /*TODO*/ }
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
                    onClick = { /*TODO*/ }
                )
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
        },
        //If Drawer should respond to drag
        gesturesEnabled = true
    ) {

    }
}

@Preview
@Composable
fun PreviewNavigation() {
    val navController = rememberNavController()
    //TopNavBarA(navController = navController) //Used in Watchlist, Following, Search,
    //TopNavBarB(navController = navController)  //Used in Settings Page, You Follow, Your Followers
    //TopNavBarC(navController = navController) //Used in profile page
    //TopNavBarD(navController = navController) //Used in Movie Details Page
    //TopNavBarE(navController = navController) //Used in Side Menu (Drawer)
    //TopNavBarF(navController = navController) //Used in Login, Register, Forgot Password Page
    NavDrawer(navController = navController)
}