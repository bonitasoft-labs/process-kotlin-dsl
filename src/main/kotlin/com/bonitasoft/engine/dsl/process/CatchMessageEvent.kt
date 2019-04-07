package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.IntermediateCatchEventDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.StartEventDefinitionBuilder

class CatchMessageEvent(parent: DataContainer, name: String) : CatchEvent(parent, name) {

    private var message : CatchMessageBuilder? = null

    fun message(name: String, init:  CatchMessageBuilder.() -> Unit = {}) {
        message = CatchMessageBuilder(name)
        message?.init()
    }

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): IntermediateCatchEventDefinitionBuilder {
        val flowNodeBuilder = super.buildFlowNode(builder)
        message?.apply { this.buildCatchMessage(flowNodeBuilder.addMessageEventTrigger(this.name)) }
        return flowNodeBuilder
    }
}