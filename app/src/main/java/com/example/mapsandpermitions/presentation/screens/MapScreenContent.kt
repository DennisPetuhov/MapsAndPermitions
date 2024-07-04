
package com.example.mapsandpermitions.presentation.screens

import android.Manifest
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.mapsandpermitions.permissions.PermissionAction
import com.example.mapsandpermitions.permissions.PermissionDialog
import com.example.mapsandpermitions.presentation.composables.MapView
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import com.example.mapsandpermitions.R



@Composable
fun MapScreenContent(
    snackbarHostState: SnackbarHostState,
    fetchLocationUpdates: () -> Unit
) {
  // 1
  val scope = rememberCoroutineScope()
  // 2
  val context = LocalContext.current
  // 3
  var showMap by rememberSaveable {
    mutableStateOf(false)
  }
  // 4
  PermissionDialog(
      context = context,
      permission = Manifest.permission.ACCESS_FINE_LOCATION,
      permissionRationale = stringResource(id = R.string.permission_location_rationale),
      snackbarHostState = snackbarHostState) { permissionAction ->
    // 5
    when (permissionAction) {
      is PermissionAction.PermissionDenied -> {
        showMap = false
      }
      is PermissionAction.PermissionGranted -> {
        showMap = true
        scope.launch {
          snackbarHostState.showSnackbar("Location permission granted!")
        }
        fetchLocationUpdates.invoke()
      }
    }
  }

  val currentLocation = LatLng(1.35, 103.87)
  if (showMap) {
    MapView(context, currentLocation)
  }
}