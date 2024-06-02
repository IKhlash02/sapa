package com.example.sapa

import android.media.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sapa.ui.navigation.NavigationItem
import com.example.sapa.ui.navigation.Screen
import com.example.sapa.ui.screen.dictionary.DictionaryScreen
import com.example.sapa.ui.screen.home.HomeScreen
import com.example.sapa.ui.screen.profile.ProfileScreen
import com.example.sapa.ui.theme.MidnightBlue
import com.example.sapa.ui.theme.SAPATheme


@Composable
fun SapaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {BottomBar(navController)}
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Dictionary.route){
                DictionaryScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
        }

    }

}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,

) {
    NavigationBar(
        
        modifier = modifier,
        containerColor = MidnightBlue
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = painterResource(id = R.drawable.home_icon),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_dictionary),
                icon = painterResource(id = R.drawable.front_hand),
                screen = Screen.Dictionary
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = painterResource(id = R.drawable.profile_icon),
                screen = Screen.Profile
            ),
        )

        navigationItem.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                label = { Text(text = item.title) },
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = { Icon(painter = item.icon, contentDescription = item.title) })
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SapaAppPreview() {
    SAPATheme {
        SapaApp()
    }
}