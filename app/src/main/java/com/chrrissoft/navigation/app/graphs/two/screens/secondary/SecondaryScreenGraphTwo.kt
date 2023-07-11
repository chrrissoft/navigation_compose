package com.chrrissoft.navigation.app.graphs.two.screens.secondary

import com.chrrissoft.navigation.arch.Screen

object SecondaryScreenGraphTwo : Screen<
        SecondaryScreenGraphTwoArguments,
       SecondaryScreenGraphTwoArgumentsKeysHolder,
        >(
    baseRoute = "secondary_screen_graph_two",
    keysHolder =SecondaryScreenGraphTwoArguments.instance,
    argumentsHolder =SecondaryScreenGraphTwoArguments.instance,
)
