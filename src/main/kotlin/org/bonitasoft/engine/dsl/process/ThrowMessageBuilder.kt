package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.IntermediateThrowEventDefinitionBuilder

class ThrowMessageBuilder(val name: String) {

    private val correlations : MutableMap<String,String> = mutableMapOf()

    var targetProcess: String? = null


    fun correlations(vararg pairs: Pair<String, String>) {
        correlations.putAll(pairs)
    }

    fun buildThrowMessage(messageBuilder: IntermediateThrowEventDefinitionBuilder) {
        val triggerBuilder = messageBuilder.addMessageEventTrigger(name, targetProcess?.toExpression())
        correlations.forEach{c->
            triggerBuilder.addCorrelation(c.key.toExpression(), c.value.toExpression())
        }
    }


}