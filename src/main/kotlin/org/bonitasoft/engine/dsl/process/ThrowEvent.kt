package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.IntermediateThrowEventDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

open class ThrowEvent(parent: org.bonitasoft.engine.dsl.process.DataContainer, name: String) : FlowNode(parent, name) {

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): IntermediateThrowEventDefinitionBuilder {
        return builder.addIntermediateThrowEvent(name)
    }
}