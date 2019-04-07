package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.CatchMessageEventTriggerDefinitionBuilder

class CatchMessageBuilder(val name: String) {

    private val correlations : MutableMap<String,String> = mutableMapOf()

    fun correlations(vararg pairs: Pair<String, String>) {
        correlations.putAll(pairs)
    }

    fun buildCatchMessage(messageBuilder: CatchMessageEventTriggerDefinitionBuilder) {
        correlations.forEach{c->
            messageBuilder.addCorrelation(c.key.toExpression(), c.value.toExpression())
        }
    }


}