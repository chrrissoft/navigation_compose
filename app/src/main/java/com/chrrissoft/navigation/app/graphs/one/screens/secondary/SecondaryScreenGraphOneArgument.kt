package com.chrrissoft.navigation.app.graphs.one.screens.secondary

import com.chrrissoft.navigation.AbstractSerializableCustomArg
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class SecondaryScreenGraphOneArgument(override val data: String) : AbstractSerializableCustomArg() {
    companion object {
        fun from(s: String) = SecondaryScreenGraphOneArgument("from $s")
        val default = SecondaryScreenGraphOneArgument("default")
    }
}