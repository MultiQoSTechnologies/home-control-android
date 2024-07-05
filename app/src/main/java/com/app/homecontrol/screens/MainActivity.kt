package com.app.homecontrol.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.app.homecontrol.navigation.nav_graph.RootNavGraph
import com.app.homecontrol.ui.theme.HomeControlTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            HomeControlTheme {
                Surface(
                    tonalElevation = 5.dp
                ) {
                    RootNavGraph(navHostController = navHostController)
                }
            }
        }
    }
}
