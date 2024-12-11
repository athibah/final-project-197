package com.example.finalproject197.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject197.screens.MainScreen
import com.example.finalproject197.screens.ParkVehicleScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    availableSpots: Int,
    occupiedSpots: Int,
    onVehicleParked: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        composable("main_screen") {
            MainScreen(
                navController = navController,
                availableSpots = availableSpots,
                occupiedSpots = occupiedSpots
            )
        }
        composable("park_vehicle_screen") {
            ParkVehicleScreen(
                navController = navController,
                availableSpots = availableSpots,
                onVehicleParked = onVehicleParked
            )
        }
    }
}
