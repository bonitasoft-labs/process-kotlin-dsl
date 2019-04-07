package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.FlowElementContainerBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

abstract class FlowNode(parent: DataContainer, val name: String) : DataContainer(parent) {

    fun build(builder: ProcessDefinitionBuilder){
        val buildFlowNode = buildFlowNode(builder)
        buildData(buildFlowNode)
    }
    abstract fun buildFlowNode(builder: ProcessDefinitionBuilder) : FlowElementContainerBuilder

}
