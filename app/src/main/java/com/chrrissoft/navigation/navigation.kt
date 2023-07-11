@file:Suppress("NAME_SHADOWING")

package com.chrrissoft.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.chrrissoft.navigation.app.graphs.one.GraphOne
import com.chrrissoft.navigation.app.graphs.one.screens.primary.PrimaryScreenGraphOne
import com.chrrissoft.navigation.app.graphs.one.screens.primary.ui.PrimaryScreenGraphOneUI
import com.chrrissoft.navigation.app.graphs.one.screens.secondary.SecondaryScreenGraphOne
import com.chrrissoft.navigation.app.graphs.one.screens.secondary.ui.SecondaryScreenGraphOneUI
import com.chrrissoft.navigation.app.graphs.one.screens.start.StartScreenGraphOne
import com.chrrissoft.navigation.app.graphs.one.screens.start.ui.StartScreenGraphOne
import com.chrrissoft.navigation.app.graphs.two.GraphTwo
import com.chrrissoft.navigation.app.graphs.two.screens.primary.PrimaryScreenGraphTwo
import com.chrrissoft.navigation.app.graphs.two.screens.primary.ui.PrimaryScreenGraphTwoUI
import com.chrrissoft.navigation.app.graphs.two.screens.secondary.SecondaryScreenGraphTwo
import com.chrrissoft.navigation.app.graphs.two.screens.secondary.ui.SecondaryScreenGraphTwoUI
import com.chrrissoft.navigation.app.graphs.two.screens.start.StartScreenGraphTwo
import com.chrrissoft.navigation.app.graphs.two.screens.start.ui.StartScreenGraphTwo
import com.chrrissoft.navigation.app.screens.one.ScreenOne
import com.chrrissoft.navigation.app.screens.one.ui.ScreenOneUI
import com.chrrissoft.navigation.app.screens.splash.SplashScreen
import com.chrrissoft.navigation.app.screens.splash.ui.SplashScreen
import com.chrrissoft.navigation.app.screens.two.ScreenTwo
import com.chrrissoft.navigation.app.screens.two.ui.ScreenTwoUI
import com.chrrissoft.navigation.app.ui.*
import java.util.*


@Composable
fun AppNavigation(viewModel: NavigationViewModel = viewModel(), onFinish: () -> Unit) {
    val controller = rememberNavController()
    val backStack by controller.currentBackStack.collectAsState()

    val (showBackDialog, changeShowBackDialog) = rememberSaveable {
        mutableStateOf(false)
    }

    fun onNavigate(popUpTo: PopUpTo?, newRoute: String) {
        if (popUpTo!=null) {
            viewModel.popBackStack(
                route = popUpTo.route,
                inclusive = popUpTo.inclusive
            )
        }
        viewModel.putBackStack(newRoute)
    }

    fun popBackStack(): Boolean {
        return controller.popBackStack().also {
            if (it) viewModel.popBackStack()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.routesStack.add(0, SplashScreen.createRoute())
        controller.enableOnBackPressed(false)
    }

    if (showBackDialog) {
        AlertDialog(
            containerColor = colorScheme.primaryContainer,
            title = {
                Text(
                    text = "Back from ${controller.currentRoute}",
                    color = colorScheme.primary,
                    fontWeight = Medium
                )
            },
            icon = {
                Icon(Icons.Rounded.Info, (null), tint = colorScheme.primary)
            },
            onDismissRequest = {
                changeShowBackDialog(false)
            },
            confirmButton = {
                Button(onClick = {
                    popBackStack().also {
                        if (!it) onFinish()
                    }
                    changeShowBackDialog(false)
                }) {
                    Text(text = "Okay", color = colorScheme.onPrimary, fontWeight = Medium)
                }
            },
            dismissButton = {
                Button(onClick = { changeShowBackDialog(false) }) {
                    Text(text = "Not", color = colorScheme.onPrimary, fontWeight = Medium)
                }
            },
        )
    }

    NavHost(controller, SplashScreen.route) {
        splashScreen {
            SplashScreen { args, _ ->
                controller.navigateAndPop(ScreenOne, args) {
                    viewModel.popBackStack()
                    viewModel.putBackStack(it)
                }
            }
        }

        screenOne { _, args ->
            ScreenOneUI(
                routesBackStack = viewModel.routesStack,
                arguments = args,
                backStack = backStack,
                onBack = {
                    if (viewModel.routesStack.size==1) changeShowBackDialog(true)
                    else popBackStack()
                },
                onNavigateScreenTwo = { args, options ->
                    controller.navigate(ScreenTwo, options, args) {
                        onNavigate(options.popUpTo, it)
                    }
                },
                onNavigateGraphOne = { args, options ->
                    controller.navigate(GraphOne, options, args) {
                        onNavigate(options.popUpTo, it)
                    }
                },
                onNavigateGraphTwo = { args, options ->
                    controller.navigate(GraphTwo, options, args) {
                        onNavigate(options.popUpTo, it)
                    }
                },
            )
        }

        screenTwo { _, args ->
            ScreenTwoUI(
                arguments = args,
                backStack = backStack,
                routesBackStack = viewModel.routesStack,
                onBack = {
                    if (viewModel.routesStack.size==1) changeShowBackDialog(true)
                    else popBackStack()
                },
                onNavigateScreenOne = { args, options ->
                    controller.navigate(ScreenOne, options, args) {
                        onNavigate(options.popUpTo, it)
                    }
                },
                onNavigateGraphOne = { args, options ->
                    controller.navigate(GraphOne, options, args) {
                        onNavigate(options.popUpTo, it)
                    }
                },
                onNavigateGraphTwo = { args, options ->
                    controller.navigate(GraphTwo, options, args) {
                        onNavigate(options.popUpTo, it)
                    }
                },
            )
        }

        graphOne { keys ->
            startScreenGraphOne { _ ->
                if (!controller.isStartScreenGraphOneCurrentDestination) return@startScreenGraphOne
                val routes = viewModel.routesStack.takeUntilGraphOne
                StartScreenGraphOne(
                    arguments = controller.getGraphOneArguments(keys),
                    routesBackStack = routes,
                    backStack = backStack,
                    onBack = {
                        if (routes.size==1) changeShowBackDialog(true)
                        else popBackStack()
                    },
                    onNavigatePrimary = { args, options ->
                        controller.navigate(PrimaryScreenGraphOne, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigateSecondary = { args, options ->
                        controller.navigate(SecondaryScreenGraphOne, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigateGraphTwo = { args, options ->
                        controller.navigate(GraphTwo, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                )
            }

            primaryScreenGraphOne { _, args ->
                PrimaryScreenGraphOneUI(
                    arguments = args,
                    backStack = backStack,
                    graphArguments = controller.getGraphOneArguments(keys),
                    routesBackStack = viewModel.routesStack.takeUntilContains("graph_one"),
                    onBack = {
                        popBackStack()
                    },
                    onNavigateStartScreen = { options ->
                        controller.navigate(StartScreenGraphOne, options) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigateSecondaryScreen = { args, options ->
                        controller.navigate(SecondaryScreenGraphOne, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigateGraphTwo = { args, options ->
                        controller.navigate(GraphTwo, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                )
            }

            secondaryScreenGraphOne { _, args ->
                SecondaryScreenGraphOneUI(
                    arguments = args,
                    backStack = backStack,
                    graphArguments = controller.getGraphOneArguments(keys),
                    routesBackStack = viewModel.routesStack.takeUntilContains("graph_one"),
                    onBack = {
                        popBackStack()
                    },
                    onNavigateStartScreen = { options ->
                        controller.navigate(StartScreenGraphOne, options) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigatePrimaryScreen = { args, options ->
                        controller.navigate(PrimaryScreenGraphOne, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigateGraphTwo = { args, options ->
                        controller.navigate(GraphTwo, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                )
            }
        }

        graphTwo { keys ->
            startScreenGraphTwo { _ ->
                if (!controller.isStartScreenGraphTwoCurrentDestination) return@startScreenGraphTwo
                val routes = viewModel.routesStack.takeUntilGraphTwo
                StartScreenGraphTwo(
                    backStack = backStack,
                    arguments = controller.getGraphTwoArguments(keys),
                    routesBackStack = routes,
                    onBack = {
                        if (routes.size==1) changeShowBackDialog(true)
                        else popBackStack()
                    },
                    onNavigatePrimary = { args, options ->
                        controller.navigate(PrimaryScreenGraphTwo, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigateSecondary = { args, options ->
                        controller.navigate(SecondaryScreenGraphTwo, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigateGraphOne = { args, options ->
                        controller.navigate(GraphOne, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                )
            }

            primaryScreenGraphTwo { _, args ->
                PrimaryScreenGraphTwoUI(
                    arguments = args,
                    backStack = backStack,
                    graphArguments = controller.getGraphTwoArguments(keys),
                    routesBackStack = viewModel.routesStack.takeUntilGraphTwo,
                    onBack = {
                        popBackStack()
                    },
                    onNavigateStartScreen = { options ->
                        controller.navigate(StartScreenGraphTwo, options) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigateSecondaryScreen = { args, options ->
                        controller.navigate(SecondaryScreenGraphTwo, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigateGraphOne = { args, options ->
                        controller.navigate(GraphOne, options, args) {
                            viewModel.routesStack.add(0, it)
                        }
                    },
                )
            }

            secondaryScreenGraphTwo { _, args ->
                SecondaryScreenGraphTwoUI(
                    arguments = args,
                    backStack = backStack,
                    graphArguments = controller.getGraphTwoArguments(keys),
                    routesBackStack = viewModel.routesStack.takeUntilGraphTwo,
                    onBack = {
                        popBackStack()
                    },
                    onNavigateStartScreen = { options ->
                        controller.navigate(StartScreenGraphTwo, options) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigatePrimaryScreen = { args, options ->
                        controller.navigate(PrimaryScreenGraphTwo, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                    onNavigateGraphOne = { args, options ->
                        controller.navigate(GraphOne, options, args) {
                            onNavigate(options.popUpTo, it)
                        }
                    },
                )
            }
        }
    }
}
