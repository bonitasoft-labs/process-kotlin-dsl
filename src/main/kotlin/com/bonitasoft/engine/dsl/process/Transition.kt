package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import java.lang.IllegalArgumentException


data class Transition(internal val source: String,
                      internal val target : String,
                      internal var default : Boolean = false,
                      internal var condition: ExpressionDSLBuilder? = null) {

    fun isDefault() {
        default = true
    }

    fun condition(init : ExpressionDSLBuilder.() -> Unit) : Transition {
        condition = ExpressionDSLBuilder().apply(init)
        return this
    }

    fun build(builder: ProcessDefinitionBuilder, dataContainer: DataContainer) {
        if (!default) {
            if (condition != null) {
                val expression = condition?.build(dataContainer, java.lang.Boolean::class.java.name)
                if (expression?.returnType != java.lang.Boolean::class.java.name) {
                    throw IllegalArgumentException("condition must return a boolean (${expression?.returnType})")
                }
                builder.addTransition(source, target, expression)
            } else {
                builder.addTransition(source, target)
            }
        } else {
            builder.addDefaultTransition(source, target)
        }
    }
}
