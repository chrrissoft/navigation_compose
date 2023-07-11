package com.chrrissoft.navigation.app.graphs.one.screens.primary

import com.chrrissoft.navigation.AbstractSerializableCustomArg
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class PrimaryScreenGraphOneArgument(override val data: String) : AbstractSerializableCustomArg() {
    companion object {
        fun from(s: String) = PrimaryScreenGraphOneArgument("from $s")

        val default = PrimaryScreenGraphOneArgument("default")
    }
}
