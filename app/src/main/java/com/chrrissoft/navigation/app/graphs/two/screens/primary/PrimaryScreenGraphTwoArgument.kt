package com.chrrissoft.navigation.app.graphs.two.screens.primary

import com.chrrissoft.navigation.AbstractSerializableCustomArg
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class PrimaryScreenGraphTwoArgument(override val data: String) : AbstractSerializableCustomArg() {
    companion object {
        fun from(s: String) = PrimaryScreenGraphTwoArgument("from $s")

        val default = PrimaryScreenGraphTwoArgument("default")
    }
}
