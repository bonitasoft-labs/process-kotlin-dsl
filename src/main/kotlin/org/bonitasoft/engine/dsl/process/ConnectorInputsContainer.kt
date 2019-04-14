package org.bonitasoft.engine.dsl.process

class ConnectorInputsContainer {

    private var intputs: MutableMap<String, org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder> = mutableMapOf()

    fun add(key: String, value: org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder) {
        intputs[key] = value
    }

    infix fun String.takes(that: org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder) {
        intputs[this] = that
    }

}