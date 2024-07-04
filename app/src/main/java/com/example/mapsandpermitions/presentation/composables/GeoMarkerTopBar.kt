package com.example.mapsandpermitions.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mapsandpermitions.ui.theme.MapsAndPermitionsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeoMarkerTopBar() {
    SmallTopAppBar(
        title = { Text("Geo Marker") },
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.inversePrimary)
    )
}

@Preview
@Composable
fun TopBarPreview() {
    MapsAndPermitionsTheme {
        GeoMarkerTopBar()
    }
}