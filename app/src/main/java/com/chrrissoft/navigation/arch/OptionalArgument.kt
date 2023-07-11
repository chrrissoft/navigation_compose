package com.chrrissoft.navigation.arch

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.chrrissoft.navigation.arch.keys.ArgumentKey

class OptionalArgument<T> constructor(
    val default: T?,
    private val type: NavType<T>,
    private val nullable: Boolean,
    val key: ArgumentKey<T>,
) {
    fun toNamedArg() : NamedNavArgument {
        return navArgument(key.name) {
            type = this@OptionalArgument.type
            nullable = this@OptionalArgument.nullable
            defaultValue = default
        }
    }

    companion object {
        fun intArg(value: Int, key: ArgumentKey<Int>): OptionalArgument<Int> {
            return OptionalArgument(type = NavType.IntType, key = key, nullable = false, default = value)
        }
    }
}
