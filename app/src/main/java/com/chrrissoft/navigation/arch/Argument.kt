package com.chrrissoft.navigation.arch

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.chrrissoft.navigation.arch.keys.ArgumentKey

class Argument<T> constructor(
    val value: T?,
    val type: NavType<T>,
    val nullable: Boolean,
    val key: ArgumentKey<T>,
) {
    fun toNamedArg(): NamedNavArgument {
        return navArgument(key.name) {
            type = this@Argument.type
            nullable = this@Argument.nullable
        }
    }

    companion object {
        fun intArg(value: Int, key: ArgumentKey<Int>): Argument<Int> {
            return Argument(type = NavType.IntType, key = key, nullable = false, value = value)
        }
    }
}
