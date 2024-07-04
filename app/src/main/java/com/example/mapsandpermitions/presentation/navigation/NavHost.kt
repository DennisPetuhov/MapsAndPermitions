package com.example.mapsandpermitions.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mapsandpermitions.presentation.GeoMarkerViewModel
import com.example.mapsandpermitions.presentation.screens.GeoMarkerScreen
import com.example.mapsandpermitions.presentation.screens.MapsScreen

@ExperimentalMaterial3Api
@Composable
fun AppNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    geoMarkerViewModel: GeoMarkerViewModel,
    fetchLocationUpdates: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.MapScreen.route
    ) {
        composable(Screens.MapScreen.route) {
            MapsScreen(
                snackbarHostState = snackbarHostState,
                navController = navController,
                fetchLocationUpdates
            )
        }
        composable(Screens.GeoMarkerScreen.route) {
            GeoMarkerScreen(geoMarkerViewModel)
        }
    }
}