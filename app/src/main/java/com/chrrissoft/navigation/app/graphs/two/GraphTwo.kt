package com.chrrissoft.navigation.app.graphs.two

import com.chrrissoft.navigation.app.graphs.two.screens.start.StartScreenGraphTwo
import com.chrrissoft.navigation.arch.Graph

object GraphTwo : Graph<
        GraphTwoArguments,
        GraphTwoArgumentsKeysHolder,
        >(
    baseRoute = "graph_two",
    args = GraphTwoArguments.instance,
    keys = GraphTwoArguments.instance,
    starDestination = StartScreenGraphTwo
)

