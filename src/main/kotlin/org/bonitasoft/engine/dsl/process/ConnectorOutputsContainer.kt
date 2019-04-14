package org.bonitasoft.engine.dsl.process

class ConnectorOutputsContainer : org.bonitasoft.engine.dsl.process.OperationContainer() {

    fun outputRef(outputName: String): org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder = org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder().apply { input(outputName) }

    infix fun String.saveToData(that: String) {
        return update(that).with(outputRef(this))
    }

    fun add(name: String, dataRef: String) = update(dataRef).with(outputRef(name))

}