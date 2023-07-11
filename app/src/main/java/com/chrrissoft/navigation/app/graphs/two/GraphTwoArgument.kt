package com.chrrissoft.navigation.app.graphs.two

import com.chrrissoft.navigation.AbstractSerializableCustomArg
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class GraphTwoArgument(override val data: String) : AbstractSerializableCustomArg() {
    companion object {
        fun from(s: String) = GraphTwoArgument("from $s")
        val default = GraphTwoArgument("default")
    }
}
