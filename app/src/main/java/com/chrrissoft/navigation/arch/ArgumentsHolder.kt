package com.chrrissoft.navigation.arch

interface ArgumentsHolder {
     val requiredArgs: List<Argument<*>> get() = emptyList()
     val requiredCustomArgs: List<CustomArgument<*>> get() = emptyList()
     val optionalsArgs: List<OptionalArgument<*>> get() = emptyList()

     object EmptyArgumentsHolder : ArgumentsHolder
}
