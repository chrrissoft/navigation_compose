package com.chrrissoft.navigation.arch

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.chrrissoft.navigation.AbstractSerializableCustomArg
import com.chrrissoft.navigation.arch.keys.CustomArgumentKey
import com.chrrissoft.navigation.parcelableOf
import kotlinx.serialization.json.JsonElement

class CustomArgument<T : AbstractSerializableCustomArg> constructor(
    val value: JsonElement?,
    private val type: NavType<T>,
    private val nullable: Boolean,
    val key: CustomArgumentKey<T>,
) {
    fun toNamedArg() : NamedNavArgument {
        return navArgument(key.name) {
            type = this@CustomArgument.type
            nullable = this@CustomArgument.nullable
        }
    }

    companion object {
        inline fun <reified D : AbstractSerializableCustomArg> custom(
            value: JsonElement, key: CustomArgumentKey<D>,
        ): CustomArgument<D> {
            return CustomArgument(
                value = value,
                type = NavType.Companion.parcelableOf(false),
                nullable = false,
                key = key,
            )
        }
    }
}
