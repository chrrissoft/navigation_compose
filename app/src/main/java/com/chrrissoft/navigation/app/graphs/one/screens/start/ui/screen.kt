package com.chrrissoft.navigation.app.graphs.one.screens.start.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import com.chrrissoft.navigation.NavOptions
import com.chrrissoft.navigation.app.graphs.one.GraphOneArguments
import com.chrrissoft.navigation.app.graphs.one.screens.primary.PrimaryScreenGraphOneArgument
import com.chrrissoft.navigation.app.graphs.one.screens.primary.PrimaryScreenGraphOneArguments
import com.chrrissoft.navigation.app.graphs.one.screens.secondary.SecondaryScreenGraphOneArgument
import com.chrrissoft.navigation.app.graphs.one.screens.secondary.SecondaryScreenGraphOneArguments
import com.chrrissoft.navigation.app.graphs.two.GraphTwoArgument
import com.chrrissoft.navigation.app.graphs.two.GraphTwoArguments
import com.chrrissoft.navigation.random

import com.chrrissoft.navigation.ui.components.*

@Composable
fun StartScreenGraphOne(
    arguments: GraphOneArguments,
    routesBackStack: List<String>,
    backStack: List<NavBackStackEntry>,
    onBack: () -> Unit,
    onNavigatePrimary: (PrimaryScreenGraphOneArguments, NavOptions) -> Unit,
    onNavigateSecondary: (SecondaryScreenGraphOneArguments, NavOptions) -> Unit,
    onNavigateGraphTwo: (GraphTwoArguments, NavOptions) -> Unit,
) {
    val (sendOptionals, onSendOptionalsChange) = rememberSaveable {
        mutableStateOf(false)
    }

    val (options, onOptionsChange) = remember {
        mutableStateOf(NavOptions())
    }

    ScreenUI(
        title = "Start Graph One",
        onBack = onBack,
        backStack = backStack,
        receivedArgs = {
            ArgsReceiver(
                title = "Graph arguments",
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
                destinationOneName = "Primary Graph One",
                destinationTwoName = "Secondary Graph One",
                destinationThreeName = "Graph Two",
                onNavigateDestinationOne = {
                    val custom = PrimaryScreenGraphOneArgument.from("start")
                    val required = random()
                    val args = PrimaryScreenGraphOneArguments(required, required, custom, custom)
                    val toSend = if (!sendOptionals) args
                    else args.copy(optional0 = random(), optional1 = random())
                    onNavigatePrimary(toSend, options)
                },
                onNavigateDestinationTwo = {
                    val custom = SecondaryScreenGraphOneArgument.from("start")
                    val required = random()
                    val args = SecondaryScreenGraphOneArguments(required, required, custom, custom)
                    val toSend = if (!sendOptionals) args
                    else args.copy(optional0 = random(), optional1 = random())
                    onNavigateSecondary(toSend, options)
                },
                onNavigateDestinationThree = {
                    val custom = GraphTwoArgument.from("start")
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
