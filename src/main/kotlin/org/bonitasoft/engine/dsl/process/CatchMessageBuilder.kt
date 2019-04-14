package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.CatchMessageEventTriggerDefinitionBuilder

class CatchMessageBuilder(val name: String) {

    private var correlations : org.bonitasoft.engine.dsl.process.CorrelationBuilder? = null


    fun correlations(init : org.bonitasoft.engine.dsl.process.CorrelationBuilder.()->Unit) {
        correlations = org.bonitasoft.engine.dsl.process.CorrelationBuilder().apply(init)
    }

    fun buildCatchMessage(messageBuilder: CatchMessageEventTriggerDefinitionBuilder, dataContainer: org.bonitasoft.engine.dsl.process.DataContainer) {
        correlations?.build(messageBuilder,dataContainer)
    }


}