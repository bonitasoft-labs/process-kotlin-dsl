package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.StartEventDefinitionBuilder

class StartMessageEvent(parent: DataContainer, name: String) : StartEvent(parent, name) {

    private var message : CatchMessageBuilder? = null

    fun message(name: String, init:  CatchMessageBuilder.() -> Unit = {}) {
        message = CatchMessageBuilder(name)
        message?.init()
    }

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): StartEventDefinitionBuilder {
        val flowNodeBuilder = super.buildFlowNode(builder)
        message?.apply { this.buildCatchMessage(flowNodeBuilder.addMessageEventTrigger(this.name)) }
        return flowNodeBuilder
    }
}