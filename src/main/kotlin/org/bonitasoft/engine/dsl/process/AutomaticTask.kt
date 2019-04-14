package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

class AutomaticTask(parent: org.bonitasoft.engine.dsl.process.DataContainer, name: String) : org.bonitasoft.engine.dsl.process.Activity(parent, name) {

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): ActivityDefinitionBuilder {
        return builder.addAutomaticTask(name)
    }
}