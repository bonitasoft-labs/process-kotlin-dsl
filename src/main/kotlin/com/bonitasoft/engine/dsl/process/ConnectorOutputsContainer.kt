package com.bonitasoft.engine.dsl.process

class ConnectorOutputsContainer : OperationContainer() {

    fun outputRef(outputName: String): ExpressionDSLBuilder = ExpressionDSLBuilder().apply { input(outputName) }

    infix fun String.saveToData(that: String) {
        return update(that).with(outputRef(this))
    }

}