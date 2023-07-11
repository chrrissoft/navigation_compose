package com.chrrissoft.navigation.app.screens.one.ui

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
import com.chrrissoft.navigation.app.screens.one.ScreenOneArguments
import com.chrrissoft.navigation.app.screens.two.CustomScreenTwoArgument
import com.chrrissoft.navigation.app.screens.two.ScreenTwoArguments
import com.chrrissoft.navigation.random
import com.chrrissoft.navigation.ui.components.*

@Composable
fun ScreenOneUI(
    routesBackStack: List<String>,
    arguments: ScreenOneArguments,
    backStack: List<NavBackStackEntry>,
    onBack: () -> Unit,
    onNavigateScreenTwo: (ScreenTwoArguments, NavOptions) -> Unit,
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
        title = "Screen One",
        onBack = onBack,
        backStack = backStack,
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
                destinationOneName = "Screen Two",
                destinationTwoName = "Graph One",
                destinationThreeName = "Graph Two",
                onNavigateDestinationOne = {
                    val custom = CustomScreenTwoArgument.from("one")
                    val required = random()
                    val args = ScreenTwoArguments(required, required, custom, custom)
                    val toSend = if (!sendOptionals) args
                    else args.copy(optional0 = random(), optional1 = random())
                    onNavigateScreenTwo(toSend, options)
                },
                onNavigateDestinationTwo = {
                    val custom = GraphOneArgument.from("one")
                    val required = random()
                    val args = GraphOneArguments(required, required, custom, custom)
                    val toSend = if (!sendOptionals) args
                    else args.copy(optional0 = random(), optional1 = random())
                    onNavigateGraphOne(toSend, options)
                },
                onNavigateDestinationThree = {
                    val custom = GraphTwoArgument.from("one")
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
