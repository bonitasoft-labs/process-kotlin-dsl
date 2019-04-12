package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

/**
 * @author Danila Mazour
 */
data class TransitionBuilder(var source: String? = null,
                             var target: String? = null,
                             var isDefault: Boolean = false,
                             var condition: ExpressionDSLBuilder? = null,
                             val transitionContainer: TransitionContainer) {


    fun isDefault() {
        isDefault = true
    }


    fun from(source: String) = this.apply { this.source = source }
    fun from(source: FlowNode) = this.apply { this.source = source.name }
    fun to(target: String): TransitionBuilder {
        if (this.target != null) {
            return transitionContainer.from(this.source!!).to(target)
        }
        this.target = target
        return this
    }

    fun to(target: FlowNode): TransitionBuilder {
        this.target = target.name
        return this
    }

    fun condition(init: ExpressionDSLBuilder.() -> Unit): TransitionBuilder {
        condition = ExpressionDSLBuilder().apply(init)
        return this
    }

    fun build(builder: ProcessDefinitionBuilder, dataContainer: DataContainer) {
        if (!isDefault) {
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
