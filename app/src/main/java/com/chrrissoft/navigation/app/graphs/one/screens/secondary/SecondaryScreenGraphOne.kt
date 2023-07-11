package com.chrrissoft.navigation.app.graphs.one.screens.secondary

import com.chrrissoft.navigation.arch.Screen

object SecondaryScreenGraphOne : Screen<
        SecondaryScreenGraphOneArguments,
       SecondaryScreenGraphOneArgumentsKeysHolder,
        >(
    baseRoute = "secondary_screen_graph_one",
    keysHolder =SecondaryScreenGraphOneArguments.instance,
    argumentsHolder = SecondaryScreenGraphOneArguments.instance,
)
