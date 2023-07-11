package com.chrrissoft.navigation.arch

abstract class Graph<A : ArgumentsHolder, K : ArgumentsKeysHolder>(
    val baseRoute: String,
    args: A,
    keys: K,
    val starDestination: Screen<*, *>
) : Screen<A, K>(baseRoute, args, keys)
