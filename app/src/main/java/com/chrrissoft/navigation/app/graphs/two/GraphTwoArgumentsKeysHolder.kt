package com.chrrissoft.navigation.app.graphs.two

import com.chrrissoft.navigation.arch.*
import com.chrrissoft.navigation.arch.keys.ArgumentKey
import com.chrrissoft.navigation.arch.keys.CustomArgumentKey

@Suppress("PropertyName")
abstract class GraphTwoArgumentsKeysHolder : ArgumentsKeysHolder {
    protected val requiredKey0 = ArgumentKey<Int>("required_0_graph_two")
    protected val requiredKey1 = ArgumentKey<Int>("required_1_graph_two")
    protected val customKey0 = CustomArgumentKey<GraphTwoArgument>("custom_0_graph_two")
    protected val customKey1 = CustomArgumentKey<GraphTwoArgument>("custom_1_graph_two")

    protected val optionalKey0 = ArgumentKey<Int>("optional_0_graph_two")
    protected val optionalKey1 = ArgumentKey<Int>("optional_1_graph_two")

    val REQUIRED_KEY_0 = requiredKey0
    val REQUIRED_KEY_1 = requiredKey1
    val CUSTOM_KEY_0 = customKey0
    val CUSTOM_KEY_1 = customKey1

    val OPTIONAL_KEY_0 = optionalKey0
    val OPTIONAL_KEY_1 = optionalKey1
}
