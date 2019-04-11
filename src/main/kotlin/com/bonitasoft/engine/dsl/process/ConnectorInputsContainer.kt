package com.bonitasoft.engine.dsl.process

class ConnectorInputsContainer {

    private var intputs: MutableMap<String, ExpressionDSLBuilder> = mutableMapOf()

    fun add(key: String, value: ExpressionDSLBuilder) {
        intputs[key] = value
    }

    infix fun String.takes(that: ExpressionDSLBuilder) {
        intputs[this] = that
    }

}