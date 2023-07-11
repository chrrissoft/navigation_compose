package com.chrrissoft.navigation.app.ui

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.chrrissoft.navigation.app.graphs.one.GraphOne
import com.chrrissoft.navigation.app.graphs.one.GraphOneArguments
import com.chrrissoft.navigation.app.graphs.one.GraphOneArgumentsKeysHolder
import com.chrrissoft.navigation.app.graphs.one.screens.primary.PrimaryScreenGraphOne
import com.chrrissoft.navigation.app.graphs.one.screens.primary.PrimaryScreenGraphOneArguments
import com.chrrissoft.navigation.app.graphs.one.screens.primary.PrimaryScreenGraphOneArgumentsKeysHolder
import com.chrrissoft.navigation.app.graphs.one.screens.secondary.SecondaryScreenGraphOne
import com.chrrissoft.navigation.app.graphs.one.screens.secondary.SecondaryScreenGraphOneArguments
import com.chrrissoft.navigation.app.graphs.one.screens.secondary.SecondaryScreenGraphOneArgumentsKeysHolder
import com.chrrissoft.navigation.app.graphs.one.screens.start.StartScreenGraphOne
import com.chrrissoft.navigation.app.graphs.two.GraphTwo
import com.chrrissoft.navigation.app.graphs.two.GraphTwoArguments
import com.chrrissoft.navigation.app.graphs.two.GraphTwoArgumentsKeysHolder
import com.chrrissoft.navigation.app.graphs.two.screens.primary.PrimaryScreenGraphTwo
import com.chrrissoft.navigation.app.graphs.two.screens.primary.PrimaryScreenGraphTwoArguments
import com.chrrissoft.navigation.app.graphs.two.screens.primary.PrimaryScreenGraphTwoArgumentsKeysHolder
import com.chrrissoft.navigation.app.graphs.two.screens.secondary.SecondaryScreenGraphTwo
import com.chrrissoft.navigation.app.graphs.two.screens.secondary.SecondaryScreenGraphTwoArguments
import com.chrrissoft.navigation.app.graphs.two.screens.secondary.SecondaryScreenGraphTwoArgumentsKeysHolder
import com.chrrissoft.navigation.app.graphs.two.screens.start.StartScreenGraphTwo
import com.chrrissoft.navigation.app.screens.one.ScreenOne
import com.chrrissoft.navigation.app.screens.one.ScreenOneArguments
import com.chrrissoft.navigation.app.screens.one.ScreenOneArgumentsKeysHolder
import com.chrrissoft.navigation.app.screens.splash.SplashScreen
import com.chrrissoft.navigation.app.screens.two.ScreenTwo
import com.chrrissoft.navigation.app.screens.two.ScreenTwoArguments
import com.chrrissoft.navigation.app.screens.two.ScreenTwoArgumentsKeysHolder
import com.chrrissoft.navigation.getCustom
import com.chrrissoft.navigation.getInt
import com.chrrissoft.navigation.graph
import com.chrrissoft.navigation.screen

//private const val TAG = "Utilities"


/************************** graph one **************************/

fun NavGraphBuilder.graphOne(
    content: NavGraphBuilder.(GraphOneArgumentsKeysHolder) -> Unit,
) {
    graph(GraphOne) { keys ->
        content(keys)
    }
}

fun NavGraphBuilder.startScreenGraphOne(
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    screen(StartScreenGraphOne) { stack, _ ->
        val arguments = stack.arguments
        requireNotNull(arguments)
        content(stack)
    }
}


fun NavGraphBuilder.primaryScreenGraphOne(
    content: @Composable (NavBackStackEntry, PrimaryScreenGraphOneArguments) -> Unit,
) {
    screen(PrimaryScreenGraphOne) { stack, screenKeys ->
        val arguments = stack.arguments
        requireNotNull(arguments)
        val args = arguments.getPrimaryScreenGraphOneArguments(screenKeys)
        content(stack, args)
    }
}

fun NavGraphBuilder.secondaryScreenGraphOne(
    content: @Composable (NavBackStackEntry, SecondaryScreenGraphOneArguments) -> Unit,
) {
    screen(SecondaryScreenGraphOne) { stack, screenKeys ->
        val arguments = stack.arguments
        requireNotNull(arguments)
        content(
            stack,
            arguments.getSecondaryScreenGraphOneArguments(screenKeys),
        )
    }
}


/************************** graph two **************************/

fun NavGraphBuilder.graphTwo(
    content: NavGraphBuilder.(GraphTwoArgumentsKeysHolder) -> Unit,
) {
    graph(GraphTwo) { keys ->
        content(keys)
    }
}

fun NavGraphBuilder.startScreenGraphTwo(
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    screen(StartScreenGraphTwo) { stack, _ ->
        val arguments = stack.arguments
        requireNotNull(arguments)
        content(stack)
    }
}

fun NavGraphBuilder.primaryScreenGraphTwo(
    content: @Composable (NavBackStackEntry, PrimaryScreenGraphTwoArguments) -> Unit,
) {
    screen(PrimaryScreenGraphTwo) { stack, screenKeys ->
        val arguments = stack.arguments
        requireNotNull(arguments)
        val args = arguments.getPrimaryScreenGraphTwoArguments(screenKeys)
        content(stack, args)
    }
}

fun NavGraphBuilder.secondaryScreenGraphTwo(
    content: @Composable (NavBackStackEntry, SecondaryScreenGraphTwoArguments) -> Unit,
) {
    screen(SecondaryScreenGraphTwo) { stack, screenKeys ->
        val arguments = stack.arguments
        requireNotNull(arguments)
        val args = arguments.getSecondaryScreenGraphTwoArguments(screenKeys)
        content(stack, args)
    }
}


/************************** splash screen **************************/

fun NavGraphBuilder.splashScreen(
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    screen(SplashScreen) { stack, _ ->
        content(stack)
    }
}


/************************** shared screens **************************/

fun NavGraphBuilder.screenOne(
    content: @Composable (NavBackStackEntry, ScreenOneArguments) -> Unit,
) {
    screen(ScreenOne) { stack, keys ->
        val arguments = stack.arguments
        requireNotNull(arguments)
        val args = arguments.getScreenOneArguments(keys)
        content(stack, args)
    }
}

fun NavGraphBuilder.screenTwo(
    content: @Composable (NavBackStackEntry, ScreenTwoArguments) -> Unit,
) {
    screen(ScreenTwo) { stack, keys ->
        val arguments = stack.arguments
        requireNotNull(arguments)
        val args = arguments.getScreenTwoArguments(keys)
        content(stack, args)
    }
}


/************************** bundle args **************************/

fun Bundle.getScreenOneArguments(keys: ScreenOneArgumentsKeysHolder): ScreenOneArguments {
    return run {
        val required0 = getInt(keys.REQUIRED_KEY_0)
        val required1 = getInt(keys.REQUIRED_KEY_1)
        val customRequired0 = getCustom(keys._CUSTOM_KEY_0)
        val customRequired1 = getCustom(keys._CUSTOM_KEY_1)

        val optional0 = getInt(keys.OPTIONAL_KEY_0)
        val optional1 = getInt(keys.OPTIONAL_KEY_1)

        ScreenOneArguments(
            required0 = required0,
            required1 = required1,
            custom0 = customRequired0,
            custom1 = customRequired1,
            optional0 = optional0,
            optional1 = optional1,
        )
    }
}

fun Bundle.getScreenTwoArguments(keys: ScreenTwoArgumentsKeysHolder): ScreenTwoArguments {
    return run {
        val required0 = getInt(keys.REQUIRED_KEY_0)
        val required1 = getInt(keys.REQUIRED_KEY_1)
        val customRequired0 = getCustom(keys.CUSTOM_KEY_0)
        val customRequired1 = getCustom(keys.CUSTOM_KEY_1)

        val optional0 = getInt(keys.OPTIONAL_KEY_0)
        val optional1 = getInt(keys.OPTIONAL_KEY_1)

        ScreenTwoArguments(
            required0 = required0,
            required1 = required1,
            custom0 = customRequired0,
            custom1 = customRequired1,
            optional0 = optional0,
            optional1 = optional1,
        )
    }
}


fun Bundle.getGraphOneArguments(keys: GraphOneArgumentsKeysHolder): GraphOneArguments {
    return run {
        val required0 = getInt(keys.REQUIRED_KEY_0)
        val required1 = getInt(keys.REQUIRED_KEY_1)
        val customRequired0 = getCustom(keys._CUSTOM_KEY_0)
        val customRequired1 = getCustom(keys._CUSTOM_KEY_1)

        val optional0 = getInt(keys.OPTIONAL_KEY_0)
        val optional1 = getInt(keys.OPTIONAL_KEY_1)

        GraphOneArguments(
            required0 = required0,
            required1 = required1,
            custom0 = customRequired0,
            custom1 = customRequired1,
            optional0 = optional0,
            optional1 = optional1,
        )
    }
}

fun Bundle.getGraphTwoArguments(keys: GraphTwoArgumentsKeysHolder): GraphTwoArguments {
    return run {
        val required0 = getInt(keys.REQUIRED_KEY_0)
        val required1 = getInt(keys.REQUIRED_KEY_1)
        val customRequired0 = getCustom(keys.CUSTOM_KEY_0)
        val customRequired1 = getCustom(keys.CUSTOM_KEY_1)

        val optional0 = getInt(keys.OPTIONAL_KEY_0)
        val optional1 = getInt(keys.OPTIONAL_KEY_1)

        GraphTwoArguments(
            required0 = required0,
            required1 = required1,
            custom0 = customRequired0,
            custom1 = customRequired1,
            optional0 = optional0,
            optional1 = optional1,
        )
    }
}


fun Bundle.getPrimaryScreenGraphOneArguments(
    keys: PrimaryScreenGraphOneArgumentsKeysHolder
): PrimaryScreenGraphOneArguments {
    return run {
        val required0 = getInt(keys.REQUIRED_KEY_0)
        val required1 = getInt(keys.REQUIRED_KEY_1)
        val customRequired0 = getCustom(keys.CUSTOM_KEY_0)
        val customRequired1 = getCustom(keys.CUSTOM_KEY_1)

        val optional0 = getInt(keys.OPTIONAL_KEY_0)
        val optional1 = getInt(keys.OPTIONAL_KEY_1)

        PrimaryScreenGraphOneArguments(
            required0 = required0,
            required1 = required1,
            custom0 = customRequired0,
            custom1 = customRequired1,
            optional0 = optional0,
            optional1 = optional1,
        )
    }
}

fun Bundle.getSecondaryScreenGraphOneArguments(
    keys: SecondaryScreenGraphOneArgumentsKeysHolder
): SecondaryScreenGraphOneArguments {
    return run {
        val required0 = getInt(keys.REQUIRED_KEY_0)
        val required1 = getInt(keys.REQUIRED_KEY_1)
        val customRequired0 = getCustom(keys.CUSTOM_KEY_0)
        val customRequired1 = getCustom(keys.CUSTOM_KEY_1)

        val optional0 = getInt(keys.OPTIONAL_KEY_0)
        val optional1 = getInt(keys.OPTIONAL_KEY_1)


        SecondaryScreenGraphOneArguments(
            required0 = required0,
            required1 = required1,
            custom0 = customRequired0,
            custom1 = customRequired1,
            optional0 = optional0,
            optional1 = optional1,
        )
    }
}


fun Bundle.getPrimaryScreenGraphTwoArguments(
    keys: PrimaryScreenGraphTwoArgumentsKeysHolder
): PrimaryScreenGraphTwoArguments {
    return run {
        val required0 = getInt(keys.REQUIRED_KEY_0)
        val required1 = getInt(keys.REQUIRED_KEY_1)
        val customRequired0 = getCustom(keys.CUSTOM_KEY_0)
        val customRequired1 = getCustom(keys.CUSTOM_KEY_1)

        val optional0 = getInt(keys.OPTIONAL_KEY_0)
        val optional1 = getInt(keys.OPTIONAL_KEY_1)

        PrimaryScreenGraphTwoArguments(
            required0 = required0,
            required1 = required1,
            custom0 = customRequired0,
            custom1 = customRequired1,
            optional0 = optional0,
            optional1 = optional1,
        )
    }
}

fun Bundle.getSecondaryScreenGraphTwoArguments(
    keys: SecondaryScreenGraphTwoArgumentsKeysHolder
): SecondaryScreenGraphTwoArguments {
    return run {
        val required0 = getInt(keys.REQUIRED_KEY_0)
        val required1 = getInt(keys.REQUIRED_KEY_1)
        val customRequired0 = getCustom(keys.CUSTOM_KEY_0)
        val customRequired1 = getCustom(keys.CUSTOM_KEY_1)

        val optional0 = getInt(keys.OPTIONAL_KEY_0)
        val optional1 = getInt(keys.OPTIONAL_KEY_1)

        SecondaryScreenGraphTwoArguments(
            required0 = required0,
            required1 = required1,
            custom0 = customRequired0,
            custom1 = customRequired1,
            optional0 = optional0,
            optional1 = optional1,
        )
    }
}
