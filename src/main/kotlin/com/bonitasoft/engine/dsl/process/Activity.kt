package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

abstract class Activity(parent: DataContainer, name: String) : FlowNode(parent, name) {

    var operationContainer: OperationContainer? = null

    fun operations(init: OperationContainer.() -> Unit) {
        operationContainer = OperationContainer().apply(init)
    }

    override fun build(builder: ProcessDefinitionBuilder): ActivityDefinitionBuilder {
        val buildFlowNode = super.build(builder)
        val activityDefinitionBuilder = buildFlowNode as ActivityDefinitionBuilder
        buildOperation(activityDefinitionBuilder)
        return activityDefinitionBuilder
    }

    private fun buildOperation(builder: ActivityDefinitionBuilder) {
        operationContainer?.operations?.forEach { op ->
            builder.addOperation(op.build(this))
        }
    }

    abstract override fun buildFlowNode(builder: ProcessDefinitionBuilder): ActivityDefinitionBuilder

}
