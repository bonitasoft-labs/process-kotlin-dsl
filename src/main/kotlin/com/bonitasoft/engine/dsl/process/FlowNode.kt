package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.FlowElementContainerBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

abstract class FlowNode(parent: DataContainer, val name: String) : DataContainer(parent) {

    open fun build(builder: ProcessDefinitionBuilder) : FlowElementContainerBuilder{
        val buildFlowNode = buildFlowNode(builder)
        buildData(buildFlowNode)
        return buildFlowNode
    }
    abstract fun buildFlowNode(builder: ProcessDefinitionBuilder) : FlowElementContainerBuilder

}
