package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.CatchMessageEventTriggerDefinitionBuilder

class CatchMessageBuilder(val name: String) {

    private var correlations : CorrelationBuilder? = null


    fun correlations(init : CorrelationBuilder.()->Unit) {
        correlations = CorrelationBuilder().apply(init)
    }

    fun buildCatchMessage(messageBuilder: CatchMessageEventTriggerDefinitionBuilder, dataContainer: DataContainer) {
        correlations?.build(messageBuilder,dataContainer)
    }


}