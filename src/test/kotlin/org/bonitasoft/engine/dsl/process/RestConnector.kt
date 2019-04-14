package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.dsl.process.connectors.Connector
import org.bonitasoft.engine.dsl.process.connectors.ConnectorDsl


fun org.bonitasoft.engine.dsl.process.ConnectorBuilder.restCall(init: RestConnectorDsl.() -> Unit) {
    RestConnectorDsl(this).apply(init)
}

class RestConnectorDsl(connectorBuilder: org.bonitasoft.engine.dsl.process.ConnectorBuilder) : ConnectorDsl(connectorBuilder, RestConnector::class.java) {

    override fun getMainResultName(): String = "myResult"

    fun url(expression: ExpressionDSLBuilder) {
        connectorBuilder.input("myInput1", expression)
    }

    fun method(expression: ExpressionDSLBuilder) {
        connectorBuilder.input("myInput2", expression)
    }

}


class RestConnector : Connector() {

    override fun execute() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}