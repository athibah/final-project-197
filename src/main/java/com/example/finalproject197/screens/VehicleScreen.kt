package com.example.finalproject197.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ParkVehicleScreen(
    navController: NavHostController,
    availableSpots: Int,
    onVehicleParked: () -> Unit
) {
    var model by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var regPlate by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Park a Vehicle",
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Available Spots: $availableSpots")
            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = model,
                onValueChange = { model = it },
                label = { Text("Car Model") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                label = { Text("Car Color") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Registration Plate Input
            OutlinedTextField(
                value = regPlate,
                onValueChange = { regPlate = it },
                label = { Text("Registration Plate") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Save Vehicle Button
            Button(
                onClick = {
                    if (model.isNotBlank() && color.isNotBlank() && regPlate.isNotBlank()) {
                        onVehicleParked() // Updates the state of the parking lot (how many avalable)
                        navController.popBackStack() // Brings user back to the og main screen
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save Vehicle")
            }
        }
    }
}
