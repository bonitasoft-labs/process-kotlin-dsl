package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

class AutomaticTask(name: String) : FlowNode(name) {

    override fun build(builder: ProcessDefinitionBuilder) {
        builder.addAutomaticTask(name)
    }
}