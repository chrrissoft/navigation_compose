package com.chrrissoft.navigation.app.screens.two.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import com.chrrissoft.navigation.NavOptions
import com.chrrissoft.navigation.app.graphs.one.GraphOneArgument
import com.chrrissoft.navigation.app.graphs.one.GraphOneArguments
import com.chrrissoft.navigation.app.graphs.two.GraphTwoArgument
import com.chrrissoft.navigation.app.graphs.two.GraphTwoArguments
import com.chrrissoft.navigation.app.screens.one.CustomScreenOneArgument
import com.chrrissoft.navigation.app.screens.one.ScreenOneArguments
import com.chrrissoft.navigation.app.screens.two.ScreenTwoArguments
import com.chrrissoft.navigation.random

import com.chrrissoft.navigation.ui.components.*

@Composable
fun ScreenTwoUI(
    arguments: ScreenTwoArguments,
    routesBackStack: List<String>,
    backStack: List<NavBackStackEntry>,
    onBack: () -> Unit,
    onNavigateScreenOne: (ScreenOneArguments, NavOptions) -> Unit,
    onNavigateGraphOne: (GraphOneArguments, NavOptions) -> Unit,
    onNavigateGraphTwo: (GraphTwoArguments, NavOptions) -> Unit,
) {
    val (sendOptionals, onSendOptionalsChange) = rememberSaveable {
        mutableStateOf(false)
    }

    val (options, onOptionsChange) = remember {
        mutableStateOf(NavOptions())
    }

    ScreenUI(
        title = "Screen Two",
        backStack = backStack,
        onBack = onBack,
        receivedArgs = {
            ArgsReceiver(
                title = "Arguments",
                required0 = arguments.required0,
                required1 = arguments.required1,
                optional0 = arguments.optional0,
                optional1 = arguments.optional1,
                custom0 = arguments.custom0,
                custom1 = arguments.custom1,
                modifier = Modifier.fillMaxSize()
            )
        },
        argsSender = {
            ArgsSender(sendOptionals, onSendOptionalsChange)
        },
        navOptionsBuilder = {
            NavOptionsBuilder(routesBackStack, options, onOptionsChange)
        },
        destinationChooser = {
            DestinationChooser(
                destinationOneName = "Screen One",
                destinationTwoName = "Graph One",
                destinationThreeName = "Graph Two",
                onNavigateDestinationOne = {
                    val custom = CustomScreenOneArgument.from("two")
                    val required = random()
                    val args = ScreenOneArguments(required, required, custom, custom)
                    val toSend = if (!sendOptionals) args
                    else args.copy(optional0 = random(), optional1 = random())
                    onNavigateScreenOne(toSend, options)
                },
                onNavigateDestinationTwo = {
                    val custom = GraphOneArgument.from("two")
                    val required = random()
                    val args = GraphOneArguments(required, required, custom, custom)
                    val toSend = if (!sendOptionals) args
                    else args.copy(optional0 = random(), optional1 = random())
                    onNavigateGraphOne(toSend, options)
                },
                onNavigateDestinationThree = {
                    val custom = GraphTwoArgument.from("two")
                    val required = random()
                    val args = GraphTwoArguments(required, required, custom, custom)
                    val toSend = if (!sendOptionals) args
                    else args.copy(optional0 = random(), optional1 = random())
                    onNavigateGraphTwo(toSend, options)
                },
            )
        },
    )
}
