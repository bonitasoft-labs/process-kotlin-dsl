package org.bonitasoft.engine.dsl.process.connectors

import org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder

abstract class ConnectorDsl(val connectorBuilder: org.bonitasoft.engine.dsl.process.ConnectorBuilder, connectorClass : Class<out Connector>) {

    init{
        connectorBuilder.connectorClass = connectorClass
    }

    abstract fun getMainResultName(): String

    fun saveResultTo(dataRef: String) {
        connectorBuilder.output(getMainResultName(), dataRef)
    }

    fun result(): ExpressionDSLBuilder = ExpressionDSLBuilder().apply { input(getMainResultName()) }
}