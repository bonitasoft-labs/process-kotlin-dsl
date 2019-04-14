package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.StartEventDefinitionBuilder

class StartMessageEvent(parent: org.bonitasoft.engine.dsl.process.DataContainer, name: String) : StartEvent(parent, name) {

    private var message : org.bonitasoft.engine.dsl.process.CatchMessageBuilder? = null

    fun message(name: String, init:  org.bonitasoft.engine.dsl.process.CatchMessageBuilder.() -> Unit = {}) {
        message = org.bonitasoft.engine.dsl.process.CatchMessageBuilder(name)
        message?.init()
    }

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): StartEventDefinitionBuilder {
        val flowNodeBuilder = super.buildFlowNode(builder)
        message?.apply { this.buildCatchMessage(flowNodeBuilder.addMessageEventTrigger(this.name), this@StartMessageEvent) }
        return flowNodeBuilder
    }
}