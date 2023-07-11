package com.chrrissoft.navigation.app.graphs.one.screens.secondary

import com.chrrissoft.navigation.arch.*
import com.chrrissoft.navigation.arch.keys.ArgumentKey
import com.chrrissoft.navigation.arch.keys.CustomArgumentKey

@Suppress("PropertyName")
abstract class SecondaryScreenGraphOneArgumentsKeysHolder : ArgumentsKeysHolder {
    protected val requiredKey0 = ArgumentKey<Int>("required_0")
    protected val requiredKey1 = ArgumentKey<Int>("required_1")
    protected val customKey0 = CustomArgumentKey<SecondaryScreenGraphOneArgument>("custom_0")
    protected val customKey1 = CustomArgumentKey<SecondaryScreenGraphOneArgument>("custom_1")

    protected val optionalKey0 = ArgumentKey<Int>("optional_0")
    protected val optionalKey1 = ArgumentKey<Int>("optional_1")

    val REQUIRED_KEY_0 = requiredKey0
    val REQUIRED_KEY_1 = requiredKey1
    val CUSTOM_KEY_0 = customKey0
    val CUSTOM_KEY_1 = customKey1

    val OPTIONAL_KEY_0 = optionalKey0
    val OPTIONAL_KEY_1 = optionalKey1
}
