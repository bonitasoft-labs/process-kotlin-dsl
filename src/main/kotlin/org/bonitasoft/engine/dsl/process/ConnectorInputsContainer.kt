package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ConnectorDefinitionBuilder

class ConnectorInputsContainer {

    private var inputs: MutableMap<String, org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder> = mutableMapOf()

    fun add(key: String, value: org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder) {
        inputs[key] = value
    }

    infix fun String.takes(that: org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder) {
        inputs[this] = that
    }

    fun build(connectorDefinitionBuilder: ConnectorDefinitionBuilder, dataContainer: DataContainer) {
        inputs.forEach { key, value ->
            connectorDefinitionBuilder.addInput(key,value.build(dataContainer))
        }
    }

}