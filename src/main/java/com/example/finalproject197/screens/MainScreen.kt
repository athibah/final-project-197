package com.example.finalproject197.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.finalproject197.R
@Composable

fun MainScreen(
    navController: NavHostController,
    availableSpots: Int,
    occupiedSpots: Int
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.car),
                contentDescription = "Car",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Parking Lot Manager",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Available Spots: $availableSpots")
            Text(text = "Occupied Spots: $occupiedSpots")
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate("park_vehicle_screen") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Park a Vehicle")
            }
        }
    }
}
