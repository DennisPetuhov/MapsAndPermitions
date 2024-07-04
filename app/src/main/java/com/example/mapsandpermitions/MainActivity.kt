package com.example.mapsandpermitions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.mapsandpermitions.presentation.GeoMarkerViewModel
import com.example.mapsandpermitions.presentation.navigation.AppNavigation
import com.example.mapsandpermitions.ui.theme.MapsAndPermitionsTheme
import com.example.mapsandpermitions.utils.locationFlow
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)

class MainActivity : ComponentActivity() {


    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private val geoMarkerViewModel: GeoMarkerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            MapsAndPermitionsTheme {
                AppNavigation(
                    navController = navController,
                    snackbarHostState = snackbarHostState,
                    geoMarkerViewModel = geoMarkerViewModel,
                    this::fetchLocationUpdates
                )
            }
        }
    }


    private fun fetchLocationUpdates() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fusedLocationClient.locationFlow().collect {
                    it?.let { location ->
                        geoMarkerViewModel.setCurrentLatLng(
                            LatLng(
                                location.latitude,
                                location.longitude
                            )
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MapsAndPermitionsTheme {

    }
}