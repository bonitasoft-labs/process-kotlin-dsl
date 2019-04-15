package org.bonitasoft.engine.dsl.process.connectors

abstract class ConnectorImplDsl(val connectorBuilder: org.bonitasoft.engine.dsl.process.ConnectorBuilder,
                                implFile : String
                                ) {

    init{
        connectorBuilder.implFile = implFile
    }
}