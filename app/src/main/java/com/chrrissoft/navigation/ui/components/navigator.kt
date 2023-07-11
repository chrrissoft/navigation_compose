package com.chrrissoft.navigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp

@Composable
fun DestinationChooser(
    destinationOneName: String,
    destinationTwoName: String,
    destinationThreeName: String,
    onNavigateDestinationOne: () -> Unit,
    onNavigateDestinationTwo: () -> Unit,
    onNavigateDestinationThree: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Center,
        horizontalAlignment = CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .clip(shapes.large)
            .background(colorScheme.primaryContainer)
            .padding(8.dp)
    ) {
        OnPrimaryContainerMediumTitle(text = "Destination chooser")
        OnPrimaryContainerDivider()
        Spacer(modifier = Modifier.height(5.dp))


        Button(
            onClick = { onNavigateDestinationOne() },
            modifier = Modifier.fillMaxWidth(),
        ) { Text(destinationOneName, color = colorScheme.onPrimary, fontWeight = Medium) }

        Button(
            onClick = { onNavigateDestinationTwo() },
            modifier = Modifier.fillMaxWidth()
        ) { Text(destinationTwoName, color = colorScheme.onPrimary, fontWeight = Medium) }

        Button(
            onClick = { onNavigateDestinationThree() },
            modifier = Modifier.fillMaxWidth()
        ) { Text(destinationThreeName, color = colorScheme.onPrimary, fontWeight = Medium) }
    }
}
