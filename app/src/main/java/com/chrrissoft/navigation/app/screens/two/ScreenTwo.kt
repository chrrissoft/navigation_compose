package com.chrrissoft.navigation.app.screens.two

import com.chrrissoft.navigation.arch.Screen

object ScreenTwo : Screen<ScreenTwoArguments, ScreenTwoArgumentsKeysHolder>(
    baseRoute = "screen_two",
    keysHolder = ScreenTwoArguments.instance,
    argumentsHolder = ScreenTwoArguments.instance,
)
