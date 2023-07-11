package com.chrrissoft.navigation

data class NavOptions(
    val launchSingleTop: Boolean = false,
    val restoreState: Boolean = false,
    val popUpTo: PopUpTo? = null,
)
