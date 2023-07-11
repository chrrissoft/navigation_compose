package com.chrrissoft.navigation
/*

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigationTest() {
    val controller = rememberNavController()

    val appGraphRoute = createRoute("app")
    val profileScreenRoute = createRoute("profile")
    val newsScreenRoute = createRoute("news")
    val homeScreenRoute = createRoute("home")
    val usernameScreenRoute = createRoute("username")
    val passwordScreenRoute = createRoute("password")
    val profileSettingsScreenRoute = createRoute("profile_settings")
    val applicationSettingsScreenRoute = createRoute("application_settings")


    val profileScreen = buildScreen {
        route = profileScreenRoute
    }
    val newsScreen = buildScreen {
        route = newsScreenRoute
        buildArguments {
            nullableIntArgument {
                name = "code"
                defaultValue = 10
            }
            longArgument {

            }
        }
        buildNavigation {
            addNavigation(homeScreenRoute)
        }
    }
    val homeScreen = buildScreen {
        route = homeScreenRoute
        buildArguments {
            nullableIntArgument {
                name = "code"
                defaultValue = 10
            }
            longArgument {

            }
        }
        buildNavigation {
            addNavigation(profileScreenRoute)
            addNavigation(newsScreenRoute)
        }
    }


    val usernameScreen = buildScreen {
        route = usernameScreenRoute
    }
    val passwordScreen = buildScreen {
        route = passwordScreenRoute
    }

    val profileSettingsScreen = buildScreen {
        route = profileSettingsScreenRoute
    }
    val applicationSettingsScreen = buildScreen {
        route = applicationSettingsScreenRoute
    }


    val settingsGraph = buildGraph {
        screens {
            screen(profileSettingsScreen)
            screen(applicationSettingsScreen)
        }
    }

    val loginGraph = buildGraph {
        screens {
            screen(usernameScreen)
            screen(passwordScreen)
        }
    }


    val appGraph = buildGraph {
        route = appGraphRoute
        startDestination = appGraphRoute

        arguments {
            longArgument {  }
        }

        screens {
            screen(profileScreen)
            screen(newsScreen)
            screen(homeScreen)
        }

        graphs {
            graph(settingsGraph)
            graph(loginGraph)
        }
    }

    buildNavigation(appGraph)
}

fun buildNavigation(graph: Graph) {

}

interface Screen
interface ScreenBuilder {
    var route: Route
    fun buildArguments(builder: ArgumentsBuilder.() -> Unit)
    fun buildNavigation(screen: NavigationBuilder.() -> Unit)
}

interface Route

fun createRoute(route: String): Route {
    TODO()
}

interface Graph
interface GraphBuilder {
    var route: Route
    var startDestination: Route

    fun arguments(route: ArgumentsBuilder.() -> Unit)
    fun screens(block: ScreensRegister.() -> Unit)
    fun graphs(block: GraphRegister.() -> Unit)

    interface ScreensRegister {
        fun screen(screen: Screen)
    }

    interface GraphRegister {
        fun graph(screen: Graph)
    }
}

interface ArgumentsBuilder {
    fun intArgument(builder: ArgumentBuilder<Int>.() -> Unit)
    fun longArgument(builder: ArgumentBuilder<Long>.() -> Unit)

    fun nullableIntArgument(builder: NullableArgumentBuilder<Int>.() -> Unit)
    fun nullableLongArgument(builder: NullableArgumentBuilder<Long>.() -> Unit)
}

interface ArgumentBuilder<T> {
    var name: String
    var defaultValue: T
}

interface NullableArgumentBuilder<T> : ArgumentBuilder<T>

interface NavigationBuilder {
    fun addNavigation(screen: Route)
}

fun buildScreen(builder: ScreenBuilder.() -> Unit): Screen {
    TODO()
}

fun buildGraph(builder: GraphBuilder.() -> Unit): Graph {
    TODO()
}*/
