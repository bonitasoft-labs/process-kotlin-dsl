package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.IntermediateThrowEventDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

class ThrowMessageEvent(parent: DataContainer, name: String) : ThrowEvent(parent, name) {

    private var message : ThrowMessageBuilder? = null

    fun message(name: String, init:  ThrowMessageBuilder.() -> Unit = {}) {
        message = ThrowMessageBuilder(name)
        message?.init()
    }

    override fun buildFlowNode(builder: ProcessDefinitionBuilder): IntermediateThrowEventDefinitionBuilder {
        val flowNodeBuilder = super.buildFlowNode(builder)
        message?.buildThrowMessage(flowNodeBuilder)
        return flowNodeBuilder
    }
}