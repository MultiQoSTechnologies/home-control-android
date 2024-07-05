package com.app.homecontrol.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.homecontrol.core.utils.fromPrettyJson
import com.app.homecontrol.navigation.ArgsConstant
import com.app.homecontrol.screens.SplashScreen
import com.app.homecontrol.screens.home.HomeScreen
import com.app.homecontrol.screens.room.RoomScreen
import com.app.homecontrol.navigation.ROOT_GRAPH_ROUTE
import com.app.homecontrol.navigation.Screen
import com.app.homecontrol.navigation.nav_args.RoomArgsModel
import com.app.homecontrol.screens.bulb.BulbScreen
import com.app.homecontrol.screens.fan.FanScreen

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = ROOT_GRAPH_ROUTE,
        startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(navHostController = navHostController)
        }
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navHostController = navHostController)
        }
        composable(
            route = Screen.Room.route,
            arguments = listOf(navArgument(ArgsConstant.roomArgs) {
                type = NavType.StringType
                defaultValue = ""
            })
        ){
            val roomArg =
                it.arguments?.getString(ArgsConstant.roomArgs)
                    ?.fromPrettyJson() as RoomArgsModel

            RoomScreen(navHostController = navHostController, roomArgsModel = roomArg)
        }
        composable(
            route = Screen.Bulb.route
        ){
            BulbScreen()
        }
        composable(
            route = Screen.Fan.route
        ){
            FanScreen()
        }
    }
}