package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.flownode.GatewayType
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

class ExclusiveGateway(name: String) : FlowNode(name) {
    override fun build(builder: ProcessDefinitionBuilder) {
        builder.addGateway(name, GatewayType.EXCLUSIVE)
    }
}
