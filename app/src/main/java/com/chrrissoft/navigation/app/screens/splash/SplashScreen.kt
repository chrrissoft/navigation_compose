package com.chrrissoft.navigation.app.screens.splash

import com.chrrissoft.navigation.arch.ArgumentsHolder.EmptyArgumentsHolder
import com.chrrissoft.navigation.arch.ArgumentsKeysHolder.EmptyArgumentsKeysHolder
import com.chrrissoft.navigation.arch.Screen

object SplashScreen : Screen<
        EmptyArgumentsHolder,
        EmptyArgumentsKeysHolder>(
    baseRoute = "splash_screen",
    keysHolder = EmptyArgumentsKeysHolder,
    argumentsHolder = EmptyArgumentsHolder,
)
