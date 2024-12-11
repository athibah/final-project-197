import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalproject197.ui.theme.ParkingLotAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkingLotAppTheme {
                App()
            }
        }
    }
}
@Composable
fun App() {
    val navController = rememberNavController()
    val totalSpots = 10 // 10 is going to be my max amount, dont wanna overload it
    var availableSpots by remember { mutableStateOf(totalSpots) }
    var occupiedSpots by remember { mutableStateOf(0) }
    val parkedVehicles = remember { mutableStateListOf<Map<String, String>>() }

    NavHost(navController = navController, startDestination = "main_screen") {
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
                onVehicleParked = { vehicle ->
                    parkedVehicles.add(vehicle)
                    availableSpots -= 1
                    occupiedSpots += 1
                },
                availableSpots = availableSpots
            )
        }
    }
}

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

@Composable
fun ParkVehicleScreen(
    navController: NavHostController,
    onVehicleParked: (Map<String, String>) -> Unit,
    availableSpots: Int
) {
    var model by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var regPlate by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
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
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Available Spots: $availableSpots")
            Spacer(modifier = Modifier.height(16.dp))

            // Vehicle Model
            OutlinedTextField(
                value = model,
                onValueChange = { model = it },
                label = { Text("Vehicle Model") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Vehicle Color
            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                label = { Text("Vehicle Color") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Registration Plate Number
            OutlinedTextField(
                value = regPlate,
                onValueChange = { regPlate = it },
                label = { Text("Registration Plate") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {})
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (model.isNotBlank() && color.isNotBlank() && regPlate.isNotBlank()) {
                        onVehicleParked(
                            mapOf("Model" to model, "Color" to color, "RegPlate" to regPlate)
                        )
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save Vehicle")
            }
        }
    }
}

// Previews

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ParkingLotAppTheme {
        MainScreen(
            navController = rememberNavController(),
            availableSpots = 7,
            occupiedSpots = 3
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ParkVehicleScreenPreview() {
    ParkingLotAppTheme {
        ParkVehicleScreen(
            navController = rememberNavController(),
            onVehicleParked = {},
            availableSpots = 5
        )
    }
}
