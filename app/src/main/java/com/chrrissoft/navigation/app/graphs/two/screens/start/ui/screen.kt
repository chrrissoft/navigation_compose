package com.chrrissoft.navigation.app.graphs.two.screens.start.ui

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
import com.chrrissoft.navigation.app.graphs.two.GraphTwoArguments
import com.chrrissoft.navigation.app.graphs.two.screens.primary.PrimaryScreenGraphTwoArgument
import com.chrrissoft.navigation.app.graphs.two.screens.primary.PrimaryScreenGraphTwoArguments
import com.chrrissoft.navigation.app.graphs.two.screens.secondary.SecondaryScreenGraphTwoArgument
import com.chrrissoft.navigation.app.graphs.two.screens.secondary.SecondaryScreenGraphTwoArguments
import com.chrrissoft.navigation.random
import com.chrrissoft.navigation.ui.components.*

@Composable
fun StartScreenGraphTwo(
    arguments: GraphTwoArguments,
    routesBackStack: List<String>,
    backStack: List<NavBackStackEntry>,
    onBack: () -> Unit,
    onNavigatePrimary: (PrimaryScreenGraphTwoArguments, NavOptions) -> Unit,
    onNavigateSecondary: (SecondaryScreenGraphTwoArguments, NavOptions) -> Unit,
    onNavigateGraphOne: (GraphOneArguments, NavOptions) -> Unit
) {
    val (sendOptionals, onSendOptionalsChange) = rememberSaveable {
        mutableStateOf(false)
    }

    val (options, onOptionsChange) = remember {
        mutableStateOf(NavOptions())
    }

    ScreenUI(
        title = "Start Graph Two",
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
                destinationOneName = "Primary Graph Two",
                destinationTwoName = "Secondary Graph Two",
                destinationThreeName = "Graph One",
                onNavigateDestinationOne = {
                    val custom = PrimaryScreenGraphTwoArgument.from("start")
                    val required = random()
                    val args = PrimaryScreenGraphTwoArguments(required, required, custom, custom)
                    val toSend = if (!sendOptionals) args
                    else args.copy(optional0 = random(), optional1 = random())
                    onNavigatePrimary(toSend, options)
                },
                onNavigateDestinationTwo = {
                    val custom = SecondaryScreenGraphTwoArgument.from("start")
                    val required = random()
                    val args = SecondaryScreenGraphTwoArguments(required, required, custom,custom)
                    val toSend = if (!sendOptionals) args
                    else args.copy(optional0 = random(), optional1 = random())
                    onNavigateSecondary(toSend, options)
                },
                onNavigateDestinationThree = {
                    val custom = GraphOneArgument.from("start")
                    val required = random()
                    val args = GraphOneArguments(required, required, custom,custom)
                    val toSend = if (!sendOptionals) args
                    else args.copy(optional0 = random(), optional1 = random())
                    onNavigateGraphOne(toSend, options)
                },
            )
        },
    )
}
