package com.chrrissoft.navigation.app.screens.two

import com.chrrissoft.navigation.AbstractSerializableCustomArg
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CustomScreenTwoArgument(override val data: String) : AbstractSerializableCustomArg() {
    companion object {
        val default = CustomScreenTwoArgument("screen two default")
        fun from(s: String) = CustomScreenTwoArgument("from $s")
    }
}
