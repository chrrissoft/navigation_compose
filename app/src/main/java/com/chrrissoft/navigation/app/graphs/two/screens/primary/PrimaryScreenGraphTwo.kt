package com.chrrissoft.navigation.app.graphs.two.screens.primary

import com.chrrissoft.navigation.arch.Screen

object PrimaryScreenGraphTwo : Screen<
        PrimaryScreenGraphTwoArguments,
        PrimaryScreenGraphTwoArgumentsKeysHolder,
        >(
    baseRoute = "primary_screen_graph_two",
    keysHolder = PrimaryScreenGraphTwoArguments.instance,
    argumentsHolder = PrimaryScreenGraphTwoArguments.instance,
)
