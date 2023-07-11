package com.chrrissoft.navigation.app.screens.one

import com.chrrissoft.navigation.arch.Screen

object ScreenOne : Screen<ScreenOneArguments, ScreenOneArgumentsKeysHolder>(
    baseRoute = "screen_one",
    keysHolder = ScreenOneArguments.instance,
    argumentsHolder = ScreenOneArguments.instance,
)
