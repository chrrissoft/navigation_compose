package com.chrrissoft.navigation

import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    val routesStack = mutableListOf<String>()

    fun popBackStack(route: String, inclusive: Boolean) {
        while (route!=routesStack.first()) routesStack.removeFirst()
        if (inclusive) routesStack.removeFirst()
    }

    fun popBackStack() {
        routesStack.removeFirst()
    }

    fun putBackStack(route: String) {
        routesStack.add(0, route)
    }
}
