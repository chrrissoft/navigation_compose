package com.chrrissoft.navigation.app.graphs.one

import com.chrrissoft.navigation.app.graphs.one.screens.start.StartScreenGraphOne
import com.chrrissoft.navigation.arch.Graph

object GraphOne : Graph<
        GraphOneArguments,
        GraphOneArgumentsKeysHolder,
        >(
    baseRoute = "graph_one",
    args = GraphOneArguments.instance,
    keys = GraphOneArguments.instance,
    starDestination = StartScreenGraphOne
)
