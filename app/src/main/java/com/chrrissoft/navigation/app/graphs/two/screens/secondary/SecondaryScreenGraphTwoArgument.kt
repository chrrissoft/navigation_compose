package com.chrrissoft.navigation.app.graphs.two.screens.secondary

import com.chrrissoft.navigation.AbstractSerializableCustomArg
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class SecondaryScreenGraphTwoArgument(override val data: String) : AbstractSerializableCustomArg() {
    companion object {
        fun from(s: String) = SecondaryScreenGraphTwoArgument("from $s")

        val default = SecondaryScreenGraphTwoArgument("default")
    }
}
