package com.example.sapa

import android.content.Context
import android.media.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sapa.ui.navigation.NavigationItem
import com.example.sapa.ui.navigation.Screen
import com.example.sapa.ui.screen.congratulation.CongratulationScreen
import com.example.sapa.ui.screen.dictionary.DictionaryScreen
import com.example.sapa.ui.screen.exam.ExamScreen
import com.example.sapa.ui.screen.home.HomeScreen
import com.example.sapa.ui.screen.intro.IntroScreen
import com.example.sapa.ui.screen.profile.ProfileScreen
import com.example.sapa.ui.screen.question.QuestionScreen
import com.example.sapa.ui.theme.LightBlue
import com.example.sapa.ui.theme.MidnightBlue
import com.example.sapa.ui.theme.PacificBlue
import com.example.sapa.ui.theme.SAPATheme


@Composable
fun SapaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Profile.route || currentRoute == Screen.Dictionary.route){
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Intro.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigatToQuestion = {
                        navController.navigate(Screen.Question.createRoute(it))
                    }
                )
            }
            composable(Screen.Dictionary.route) {
                DictionaryScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.Question.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("id") ?: 0
               if(id%4 != 0){
                   QuestionScreen(
                       id = id,
                       navigateBack = {
                           navController.navigateUp()
                       },
                       navigateFinish = {
                           navController.popBackStack()
                           navController.navigate(Screen.Congratulation.route)
                       })
               } else{
                   ExamScreen(
                       navigateBack = {
                           navController.navigateUp()
                       }
                   )
               }
            }

            composable(
                route = Screen.Congratulation.route,
            ){
                CongratulationScreen(
                    navigateBack = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route){
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable(
                route = Screen.Intro.route
            ){
                IntroScreen (
                    navigateHome = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route){
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )


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
        containerColor = MidnightBlue,
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
                icon = { Icon(painter = item.icon, contentDescription = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White,
                    indicatorColor = LightBlue
                )
            )

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