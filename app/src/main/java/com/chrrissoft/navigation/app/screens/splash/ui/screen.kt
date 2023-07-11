package com.chrrissoft.navigation.app.screens.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import com.chrrissoft.navigation.NavOptions
import com.chrrissoft.navigation.app.screens.one.CustomScreenOneArgument.Companion.from
import com.chrrissoft.navigation.app.screens.one.ScreenOneArguments
import com.chrrissoft.navigation.app.screens.one.ScreenOneArguments.Companion.optionalDefault
import com.chrrissoft.navigation.random
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random.Default.nextBoolean

@Composable
fun SplashScreen(
    onNavigateScreenOne: (ScreenOneArguments, NavOptions) -> Unit,
) {
    val scope = rememberCoroutineScope()

    val argument = run {

        val optional = if (nextBoolean()) random() else optionalDefault

        ScreenOneArguments(
            required0 = random(),
            required1 = random(),
            custom0 = from(from = "splash"),
            custom1 = from(from = "splash"),
            optional0 = optional,
            optional1 = optional,
        )
    }

    val time = 2000L

    LaunchedEffect(Unit) {
        scope.launch {
            delay(time)
            onNavigateScreenOne(argument, NavOptions())
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(colorScheme.primaryContainer),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Splash Screen", color = colorScheme.primary, fontSize = 35.sp, fontWeight = Bold)
    }

}
