package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.IntermediateCatchEventDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

open class CatchEvent(parent: org.bonitasoft.engine.dsl.process.DataContainer, name: String) : org.bonitasoft.engine.dsl.process.FlowNode(parent, name) {

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): IntermediateCatchEventDefinitionBuilder {
        return builder.addIntermediateCatchEvent(name)
    }
}