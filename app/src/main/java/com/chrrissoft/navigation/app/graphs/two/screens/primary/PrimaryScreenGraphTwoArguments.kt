package com.chrrissoft.navigation.app.graphs.two.screens.primary

import com.chrrissoft.navigation.app.graphs.two.screens.primary.PrimaryScreenGraphTwoArgument.Companion.default
import com.chrrissoft.navigation.arch.Argument
import com.chrrissoft.navigation.arch.ArgumentsHolder
import com.chrrissoft.navigation.arch.CustomArgument
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import com.chrrissoft.navigation.arch.OptionalArgument.Companion.intArg as optionalIntArg

data class PrimaryScreenGraphTwoArguments(
    val required0: Int,
    val required1: Int,
    val custom0: PrimaryScreenGraphTwoArgument,
    val custom1: PrimaryScreenGraphTwoArgument,
    val optional0: Int = optionalDefault,
    val optional1: Int = optionalDefault,
) : PrimaryScreenGraphTwoArgumentsKeysHolder(), ArgumentsHolder {
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
        val instance = PrimaryScreenGraphTwoArguments((0), (0), default, default)
    }
}
