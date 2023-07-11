package com.chrrissoft.navigation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.navigation.ui.theme.NavigationTheme

@Composable
fun AppContainer(
    content: @Composable () -> Unit
) {
    NavigationTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = colorScheme.background) {
            content()
        }
    }
}
