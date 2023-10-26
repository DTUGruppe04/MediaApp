package com.example.mediaapp.ui.nav

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediaapp.BottomNavItem
import com.example.mediaapp.R

@Composable
fun BottomNavBar(navController: NavController, modifier : Modifier = Modifier) {
    val containerColor = colorResource(R.color.black_navbar)
    val contentColor = colorResource(R.color.white_navitem)
    val indicatorColor = colorResource(R.color.indicator_color_navbar)

    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Home",
            route = "home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            name = "Following",
            route = "following",
            selectedIcon = Icons.Filled.Subscriptions,
            unselectedIcon = Icons.Outlined.Subscriptions
        ),
        BottomNavItem(
            name = "Watchlist",
            route = "watchlist",
            selectedIcon = Icons.Filled.FormatListBulleted,
            unselectedIcon = Icons.Outlined.FormatListBulleted
        ),
        BottomNavItem(
            name = "Search",
            route = "search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        ),
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

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
            Text("Media App")
        }
    )
}

/**
 * TopNavBarB
 *
 * This function has a Hamburger to the left, a centered name, and a trailing-icon to the right. See Figma for a more precise
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
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "menu"
                )
            }
        },
        title = {
            Text("Media App")
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
                Text(text = "Edit")
            }
        }
    )
}

/**
 * TopNavBarC
 *
 * This function has a left_arrow icon to the left and a name. See Figma for a more precise
 * example or preview the function
 *
 * @param navController navigation controller
 * @param modifier optional
 * @return Returns a Top Navigation Bar of type D.
 */
@Composable
fun TopNavBarD() {

}

/**
 * TopNavBarC
 *
 * This function has a axe icon to the left and a name. See Figma for a more precise
 * example or preview the function
 *
 * @param navController navigation controller
 * @param modifier optional
 * @return Returns a Top Navigation Bar of type D.
 */
@Composable
fun TopNavBarE() {

}

@Preview
@Composable
fun PreviewNavigation() {
    val navController = rememberNavController()
    TopNavBarC(navController = navController)
}