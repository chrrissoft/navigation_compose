package com.chrrissoft.navigation.app.screens.one

import com.chrrissoft.navigation.app.screens.one.CustomScreenOneArgument.Companion.default
import com.chrrissoft.navigation.arch.ArgumentsHolder
import com.chrrissoft.navigation.arch.CustomArgument.Companion.custom
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import com.chrrissoft.navigation.arch.Argument.Companion.intArg as requiredIntArg
import com.chrrissoft.navigation.arch.OptionalArgument.Companion.intArg as optionalIntArg

data class ScreenOneArguments(
    val required0: Int,
    val required1: Int,
    val custom0: CustomScreenOneArgument,
    val custom1: CustomScreenOneArgument,
    val optional0: Int = optionalDefault,
    val optional1: Int = optionalDefault,
) : ScreenOneArgumentsKeysHolder(), ArgumentsHolder {
    override val requiredArgs = listOf(
        requiredIntArg(required0, requiredKey0),
        requiredIntArg(required1, requiredKey1),
    )

    override val requiredCustomArgs = listOf(
        custom(Json.encodeToJsonElement(custom0), customKey0),
        custom(Json.encodeToJsonElement(custom1), customKey1),
    )

    override val optionalsArgs = listOf(
        optionalIntArg(optional0, optionalKey0),
        optionalIntArg(optional1, optionalKey1),
    )

    companion object {
        const val optionalDefault = 7
        val instance = ScreenOneArguments((0), (0), default, default)
    }
}
