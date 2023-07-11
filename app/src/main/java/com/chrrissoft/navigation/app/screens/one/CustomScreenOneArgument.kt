package com.chrrissoft.navigation.app.screens.one

import com.chrrissoft.navigation.AbstractSerializableCustomArg
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class CustomScreenOneArgument(override val data: String) : AbstractSerializableCustomArg() {
    companion object {
        val default = CustomScreenOneArgument("screen one default")
        fun from(from: String) = CustomScreenOneArgument("from $from")
    }
}
