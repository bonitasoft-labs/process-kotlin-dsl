package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.flownode.GatewayType
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

class InclusiveGateway(name: String) : FlowNode(name) {
    override fun build(builder: ProcessDefinitionBuilder) {
        builder.addGateway(name, GatewayType.PARALLEL)
    }
}