package com.chrrissoft.navigation.arch

abstract class Screen<
        A : ArgumentsHolder,
        K : ArgumentsKeysHolder,
        >(
    private val baseRoute: String,
    private val argumentsHolder: A,
    val keysHolder: K,
) {
    val route: String = run {
        val required = argumentsHolder.requiredArgs.map {
            "/{${it.key.name}}"
        }
        val optionals = argumentsHolder.optionalsArgs.mapIndexed { i, arg ->
            val prefix = if (i==0) "?" else "&"
            "$prefix${arg.key.name}={${arg.key.name}}"
        }

        val customRequired = argumentsHolder.requiredCustomArgs.map {
            "/{${it.key.name}}"
        }

        baseRoute.toList()
            .asSequence()
            .plus(required)
            .plus(customRequired)
            .plus(optionals)
            .joinToString()
            .replace(",", "")
            .replace(" ", "")
    }

    val arguments = run {
        val required = argumentsHolder.requiredArgs.map {
            it.toNamedArg()
        }
        val optionals = argumentsHolder.optionalsArgs.map {
            it.toNamedArg()
        }

        val customRequired = argumentsHolder.requiredCustomArgs.map {
            it.toNamedArg()
        }

        required.plus(customRequired).plus(optionals)
    }

    fun createRoute(args: A): String {
        return createRouteInternal(args)
    }

    fun createRoute(): String {
        return createRouteInternal(ArgumentsHolder.EmptyArgumentsHolder)
    }

    private fun createRouteInternal(screenArguments: ArgumentsHolder): String {
        val required = screenArguments.requiredArgs.map {
            "/${it.value}"
        }
        val optionals = screenArguments.optionalsArgs.mapIndexed { i, arg ->
            val prefix = if (i==0) "?" else "&"
            "$prefix${arg.key.name}=${arg.default}"
        }

        val customRequired = screenArguments.requiredCustomArgs.map {
            "/${it.value}"
        }
        return baseRoute
            .toList()
            .asSequence()
            .plus(required)
            .plus(customRequired)
            .plus(optionals)
            .joinToString()
            .replace(",", "")
            .replace(" ", "")
    }
}
