package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.CatchMessageEventTriggerDefinitionBuilder

class CorrelationBuilder {

    private data class CorrelationPair(val key: String, val value: ExpressionDSLBuilder)

    private val correlations: MutableList<CorrelationPair> = mutableListOf()

    infix fun String.mustMatch(expression: ExpressionDSLBuilder) {
        correlations.add(CorrelationPair(this, expression))
    }

    fun build(messageBuilder: CatchMessageEventTriggerDefinitionBuilder, dataContainer: DataContainer) {
        correlations.forEach { c -> messageBuilder.addCorrelation(c.key.toExpression(), c.value.build(dataContainer)) }

    }
}
