package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.StartEventDefinitionBuilder

open class StartEvent(parent: org.bonitasoft.engine.dsl.process.DataContainer, name: String) : FlowNode(parent, name) {

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): StartEventDefinitionBuilder {
        return builder.addStartEvent(name)
    }
}