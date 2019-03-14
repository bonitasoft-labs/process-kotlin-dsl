package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

abstract class FlowNode(val name: String) {

    abstract fun build(builder: ProcessDefinitionBuilder)

}
