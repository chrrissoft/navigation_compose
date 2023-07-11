package com.chrrissoft.navigation

data class PopUpTo(
    val route: String,
    val inclusive: Boolean = false,
    val saveState: Boolean = false
)
