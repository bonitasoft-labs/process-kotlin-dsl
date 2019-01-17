package com.bonitasoft.engine.dsl.process

import java.util.*

data class Transition(var target: String, var default: Boolean = false, var condition: Expression? = null) {

    fun condition(name: String = UUID.randomUUID().toString(), init: Expression.() -> Unit) {
        val expression = Expression(name)
        expression.init()
        condition = expression
    }
}