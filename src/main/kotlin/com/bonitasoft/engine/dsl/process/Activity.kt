package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

abstract class Activity(parent: DataContainer, name: String) : FlowNode(parent, name) {

    var operationContainer: OperationContainer? = null

    fun operations(init: OperationContainer.() -> Unit) {
        operationContainer = OperationContainer().apply(init)
    }

    override fun build(builder: ProcessDefinitionBuilder, businessArchiveBuilder: BusinessArchiveBuilder): ActivityDefinitionBuilder {
        val buildFlowNode = super.build(builder, businessArchiveBuilder)
        val activityDefinitionBuilder = buildFlowNode as ActivityDefinitionBuilder
        buildOperation(activityDefinitionBuilder)
        return activityDefinitionBuilder
    }

    private fun buildOperation(builder: ActivityDefinitionBuilder) {
        operationContainer?.build(builder, this)
    }

    abstract override fun buildFlowNode(builder: ProcessDefinitionBuilder): ActivityDefinitionBuilder

}
