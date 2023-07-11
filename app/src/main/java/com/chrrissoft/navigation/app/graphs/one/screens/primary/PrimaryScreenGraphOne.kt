package com.chrrissoft.navigation.app.graphs.one.screens.primary

import com.chrrissoft.navigation.arch.Screen

object PrimaryScreenGraphOne : Screen<
        PrimaryScreenGraphOneArguments,
       PrimaryScreenGraphOneArgumentsKeysHolder,
        >(
    baseRoute = "primary_screen_graph_one",
    keysHolder = PrimaryScreenGraphOneArguments.instance,
    argumentsHolder =PrimaryScreenGraphOneArguments.instance,
)
