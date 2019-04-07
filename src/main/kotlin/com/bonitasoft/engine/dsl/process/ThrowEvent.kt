package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.IntermediateThrowEventDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

open class ThrowEvent(parent: DataContainer, name: String) : FlowNode(parent, name) {

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): IntermediateThrowEventDefinitionBuilder {
        return builder.addIntermediateThrowEvent(name)
    }
}