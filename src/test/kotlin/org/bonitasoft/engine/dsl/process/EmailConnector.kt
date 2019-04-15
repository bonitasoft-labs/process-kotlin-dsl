package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.dsl.process.connectors.ConnectorImplDsl

fun org.bonitasoft.engine.dsl.process.ConnectorBuilder.email(init: EmailConnectorDsl.() -> Unit) {
    EmailConnectorDsl(this).apply(init)
}
@ProcessDSLMarker
class EmailConnectorDsl (connectorBuilder: org.bonitasoft.engine.dsl.process.ConnectorBuilder) : ConnectorImplDsl(connectorBuilder, "email.impl") {

    fun from(expression: ExpressionDSLBuilder) {
        connectorBuilder.input("from", expression)
    }

    fun to(expression: ExpressionDSLBuilder) {
        connectorBuilder.input("to", expression)
    }

    fun subject(expression: ExpressionDSLBuilder) {
        connectorBuilder.input("subject", expression)
    }
    fun message(expression: ExpressionDSLBuilder) {
        connectorBuilder.input("message", expression)
    }

}