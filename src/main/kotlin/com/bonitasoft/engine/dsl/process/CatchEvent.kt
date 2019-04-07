package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.IntermediateCatchEventDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.StartEventDefinitionBuilder

open class CatchEvent(parent: DataContainer, name: String) : FlowNode(parent, name) {

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): IntermediateCatchEventDefinitionBuilder {
        return builder.addIntermediateCatchEvent(name)
    }
}