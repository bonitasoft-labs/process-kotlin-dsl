package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

class AutomaticTask(parent: DataContainer, name: String) : Activity(parent, name) {

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): ActivityDefinitionBuilder {
        return builder.addAutomaticTask(name)
    }
}