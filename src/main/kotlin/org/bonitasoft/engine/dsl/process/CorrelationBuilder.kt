package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.CatchMessageEventTriggerDefinitionBuilder

class CorrelationBuilder {

    private data class CorrelationPair(val key: String, val value: org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder)

    private val correlations: MutableList<org.bonitasoft.engine.dsl.process.CorrelationBuilder.CorrelationPair> = mutableListOf()

    infix fun String.mustMatch(expression: org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder) {
        correlations.add(org.bonitasoft.engine.dsl.process.CorrelationBuilder.CorrelationPair(this, expression))
    }

    fun build(messageBuilder: CatchMessageEventTriggerDefinitionBuilder, dataContainer: org.bonitasoft.engine.dsl.process.DataContainer) {
        correlations.forEach { c -> messageBuilder.addCorrelation(c.key.toExpression(), c.value.build(dataContainer)) }

    }
}
