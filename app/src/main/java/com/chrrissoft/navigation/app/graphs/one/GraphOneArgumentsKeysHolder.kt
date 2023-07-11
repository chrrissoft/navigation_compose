package com.chrrissoft.navigation.app.graphs.one

import com.chrrissoft.navigation.arch.*
import com.chrrissoft.navigation.arch.keys.ArgumentKey
import com.chrrissoft.navigation.arch.keys.CustomArgumentKey

@Suppress("PropertyName")
abstract class GraphOneArgumentsKeysHolder : ArgumentsKeysHolder {
    protected val requiredKey0 = ArgumentKey<Int>("required_0_graph_one")
    protected val requiredKey1 = ArgumentKey<Int>("required_1_graph_one")
    protected val customKey0 = CustomArgumentKey<GraphOneArgument>("custom_0_graph_one")
    protected val customKey1 = CustomArgumentKey<GraphOneArgument>("custom_1_graph_one")

    protected val optionalKey0 = ArgumentKey<Int>("optional_0_graph_one")
    protected val optionalKey1 = ArgumentKey<Int>("optional_1_graph_one")

    val REQUIRED_KEY_0 = requiredKey0
    val REQUIRED_KEY_1 = requiredKey1
    val _CUSTOM_KEY_0 = customKey0
    val _CUSTOM_KEY_1 = customKey1

    val OPTIONAL_KEY_0 = optionalKey0
    val OPTIONAL_KEY_1 = optionalKey1
}
