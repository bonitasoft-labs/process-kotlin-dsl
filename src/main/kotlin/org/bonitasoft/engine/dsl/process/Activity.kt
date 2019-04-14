package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

@ProcessDSLMarker
abstract class Activity(parent: org.bonitasoft.engine.dsl.process.DataContainer, name: String) : org.bonitasoft.engine.dsl.process.FlowNode(parent, name) {

    var operationContainer: org.bonitasoft.engine.dsl.process.OperationContainer? = null

    fun operations(init: org.bonitasoft.engine.dsl.process.OperationContainer.() -> Unit) {
        operationContainer = org.bonitasoft.engine.dsl.process.OperationContainer().apply(init)
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
