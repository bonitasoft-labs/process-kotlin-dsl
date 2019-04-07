package com.bonitasoft.engine.dsl.process

class ConnectorInputsContainer {

    private var intputs: MutableMap<String, ExpressionDSLBuilder> = mutableMapOf()


    infix fun String.takes(that: ExpressionDSLBuilder) {
        intputs.put(this, that)
    }

}