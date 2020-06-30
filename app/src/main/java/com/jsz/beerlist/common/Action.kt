package com.jsz.beerlist.common

class Action<in T>(
    private val action: (T) -> Unit
) {

    operator fun invoke(event: T) = action(event)

    override fun equals(other: Any?): Boolean = when {
        other === this -> true
        other is Action<*> -> true
        else -> false
    }

    override fun hashCode(): Int = 0

    override fun toString(): String = "Action@${super.hashCode()}"

}

val NoOp = Action<Any?> {}

operator fun Action<Unit>.invoke() = invoke(Unit)
