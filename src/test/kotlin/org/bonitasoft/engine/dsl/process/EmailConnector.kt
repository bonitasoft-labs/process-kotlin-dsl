package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.dsl.process.connectors.ConnectorImplDsl

/*
<definitionId>email</definitionId>
	<definitionVersion>1.0.0</definitionVersion>
    <description>Implementation based on javax.mail 1.4.5</description>
	<implementationClassname>org.bonitasoft.connectors.email.EmailConnector</implementationClassname>
	<implementationId>email-impl</implementationId>
	<implementationVersion>1.0.12</implementationVersion>
 */
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