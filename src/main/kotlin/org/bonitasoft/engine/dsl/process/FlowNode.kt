package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.process.impl.FlowElementContainerBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

abstract class FlowNode(parent: org.bonitasoft.engine.dsl.process.DataContainer, val name: String) : org.bonitasoft.engine.dsl.process.DataContainer(parent) {

    open fun build(builder: ProcessDefinitionBuilder, businessArchiveBuilder: BusinessArchiveBuilder) : FlowElementContainerBuilder{
        val buildFlowNode = buildFlowNode(builder)
        buildData(buildFlowNode, businessArchiveBuilder)
        return buildFlowNode
    }
    abstract fun buildFlowNode(builder: ProcessDefinitionBuilder) : FlowElementContainerBuilder

}
