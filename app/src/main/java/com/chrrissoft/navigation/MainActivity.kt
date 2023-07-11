package com.chrrissoft.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.chrrissoft.navigation.ui.components.AppContainer
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContainer {
                AppNavigation { finish() }
                setBarsColors()
            }
        }
    }

    companion object {
        @SuppressLint("ComposableNaming")
        @Composable
        fun setBarsColors(
            bottom: Color = colorScheme.onPrimary,
            status: Color = colorScheme.primaryContainer,
        ) {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !isSystemInDarkTheme()

            DisposableEffect(systemUiController, useDarkIcons) {
                systemUiController.setStatusBarColor(status, useDarkIcons)
                systemUiController.setNavigationBarColor(bottom)
                onDispose {}
            }
        }
    }
}
