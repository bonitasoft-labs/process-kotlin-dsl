package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.FlowElementContainerBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

class AutomaticTask(parent: DataContainer, name: String) : FlowNode(parent, name) {

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): FlowElementContainerBuilder {
        return builder.addAutomaticTask(name)
    }
}