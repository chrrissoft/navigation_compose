package com.chrrissoft.navigation.app.graphs.two.screens.secondary

import com.chrrissoft.navigation.app.graphs.two.screens.secondary.SecondaryScreenGraphTwoArgument.Companion.default
import com.chrrissoft.navigation.arch.Argument
import com.chrrissoft.navigation.arch.ArgumentsHolder
import com.chrrissoft.navigation.arch.CustomArgument
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import com.chrrissoft.navigation.arch.OptionalArgument.Companion.intArg as optionalIntArg

data class SecondaryScreenGraphTwoArguments(
    val required0: Int,
    val required1: Int,
    val custom0: SecondaryScreenGraphTwoArgument,
    val custom1: SecondaryScreenGraphTwoArgument,
    val optional0: Int = optionalDefault,
    val optional1: Int = optionalDefault,
) : SecondaryScreenGraphTwoArgumentsKeysHolder(), ArgumentsHolder {
    override val requiredArgs = listOf(
        Argument.intArg(required0, requiredKey0),
        Argument.intArg(required1, requiredKey1),
    )

    override val requiredCustomArgs = listOf(
        CustomArgument.custom(Json.encodeToJsonElement(custom0), customKey0),
        CustomArgument.custom(Json.encodeToJsonElement(custom1), customKey1),
    )

    override val optionalsArgs = listOf(
        optionalIntArg(optional0, optionalKey0),
        optionalIntArg(optional1, optionalKey1),
    )

    companion object {
        const val optionalDefault = 7
        val instance = SecondaryScreenGraphTwoArguments((0), (0), default, default)
    }
}
