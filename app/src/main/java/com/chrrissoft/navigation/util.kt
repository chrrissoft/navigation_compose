package com.chrrissoft.navigation

import android.os.Build
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.chrrissoft.navigation.app.graphs.one.GraphOne
import com.chrrissoft.navigation.app.graphs.one.GraphOneArguments
import com.chrrissoft.navigation.app.graphs.one.GraphOneArgumentsKeysHolder
import com.chrrissoft.navigation.app.graphs.one.screens.start.StartScreenGraphOne
import com.chrrissoft.navigation.app.graphs.two.GraphTwo
import com.chrrissoft.navigation.app.graphs.two.GraphTwoArguments
import com.chrrissoft.navigation.app.graphs.two.GraphTwoArgumentsKeysHolder
import com.chrrissoft.navigation.app.graphs.two.screens.start.StartScreenGraphTwo
import com.chrrissoft.navigation.app.ui.getGraphOneArguments
import com.chrrissoft.navigation.app.ui.getGraphTwoArguments
import com.chrrissoft.navigation.arch.ArgumentsHolder
import com.chrrissoft.navigation.arch.ArgumentsKeysHolder
import com.chrrissoft.navigation.arch.Graph
import com.chrrissoft.navigation.arch.Screen
import com.chrrissoft.navigation.arch.keys.ArgumentKey
import com.chrrissoft.navigation.arch.keys.CustomArgumentKey
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.math.absoluteValue
import kotlin.random.Random


/****************************** nav host builder ******************************/

fun <A : ArgumentsHolder, K : ArgumentsKeysHolder> NavGraphBuilder.graph(
    graph: Graph<A, K>,
    content: NavGraphBuilder.(K) -> Unit,
) {
    navigation(
        startDestination = graph.starDestination.route,
        route = graph.route,
        graph.arguments
    ) {
        content(graph.keysHolder)
    }
}

fun <A : ArgumentsHolder, K : ArgumentsKeysHolder> NavGraphBuilder.screen(
    screen: Screen<A, K>,
    content: @Composable (NavBackStackEntry, K) -> Unit,
) {
    composable(screen.route, screen.arguments) {
        content(it, screen.keysHolder)
    }
}


/****************************** serialization ******************************/

fun Bundle.getInt(key: ArgumentKey<Int>): Int {
    return getInt(key.name)
}

inline fun <reified D : AbstractSerializableCustomArg> Bundle.getCustom(key: CustomArgumentKey<D>): D {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        getParcelable(key.name, D::class.java)!!
    else getParcelable(key.name)!!
}

inline fun <reified T : AbstractSerializableCustomArg> NavType.Companion.parcelableOf(nullable: Boolean) =
    object : NavType<T>(nullable) {
        override fun get(bundle: Bundle, key: String): T? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(key, T::class.java)
            } else {
                bundle.getParcelable(key)
            }
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString(value)
        }

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }

    }


/****************************** nav host controller ******************************/

val NavHostController.currentRoute
    get() = run {
        currentDestination?.route?.route ?: IllegalStateException()
    }

fun NavHostController.getGraphOneArguments(key: GraphOneArgumentsKeysHolder): GraphOneArguments {
    return getBackStackEntry(GraphOne.route).arguments!!.getGraphOneArguments(key)
}

fun NavHostController.getGraphTwoArguments(key: GraphTwoArgumentsKeysHolder): GraphTwoArguments {
    return getBackStackEntry(GraphTwo.route).arguments!!.getGraphTwoArguments(key)
}

fun <A : ArgumentsHolder> NavHostController.navigate(
    destination: Screen<A, *>,
    options: NavOptions,
    args: A? = null,
    beforeNavigate: NavHostController.(String) -> Unit = {}
) {
    val route = if (args!=null) destination.createRoute(args)
    else destination.createRoute()

    val routeToSave = if (destination !is Graph) route
    else destination.starDestination.route

    beforeNavigate(routeToSave)
    if (options.popUpTo!=null) {
        popBackStack(
            route = options.popUpTo.route,
            inclusive = options.popUpTo.inclusive,
            saveState = options.popUpTo.saveState
        )
    }
    navigate(route) {
        launchSingleTop = options.launchSingleTop
        restoreState = options.restoreState
    }
}

fun <A : ArgumentsHolder> NavHostController.navigateAndPop(
    destination: Screen<A, *>,
    args: A? = null,
    options: NavOptions = NavOptions(),
    beforeNavigate: NavHostController.(String) -> Unit = {}
) {
    popBackStack()
    navigate(destination, options, args, beforeNavigate)
}

val NavHostController.isStartScreenGraphOneCurrentDestination
    get() = run {
        isCurrentDestination(StartScreenGraphOne)
    }

val NavHostController.isStartScreenGraphTwoCurrentDestination
    get() = run {
        isCurrentDestination(StartScreenGraphTwo)
    }

fun NavHostController.isCurrentDestination(destination: Screen<*, *>): Boolean {
    return currentDestination?.route==destination.route
}


fun random() = Random.nextInt(1, 1000000).absoluteValue

inline fun <T> Collection<T>.forEachIndexedReverse(block: (Int, T) -> Unit) {
    var index = size - 1
    forEach {
        block(index--, it)
    }
}

val String.route
    get() = run {
        takeWhile { it.toString()!="/" }.replace("_", " ")
    }

fun List<String>.takeUntilContains(to: String): List<String> {
    return takeWhile { it.contains(to) }
}

val List<String>.takeUntilGraphOne
    get() = run {
        takeUntilContains(GraphOne.baseRoute)
    }

val List<String>.takeUntilGraphTwo
    get() = run {
        takeUntilContains(GraphTwo.baseRoute)
    }