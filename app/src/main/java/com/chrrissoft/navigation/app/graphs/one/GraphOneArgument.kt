package com.chrrissoft.navigation.app.graphs.one

import com.chrrissoft.navigation.AbstractSerializableCustomArg
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class GraphOneArgument(override val data: String) : AbstractSerializableCustomArg() {
    companion object {
        val default = GraphOneArgument("default")
        fun from(s: String) = GraphOneArgument("from $s")
    }
}
