package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.operation.Operation
import org.bonitasoft.engine.operation.OperationBuilder

@ProcessDSLMarker
class OperationDSLBuilder(val elementToUpdate: String) {

    var with: ExpressionDSLBuilder? = null

    fun with(expression: ExpressionDSLBuilder) {
        with = expression
    }

    fun build(dataContainer: org.bonitasoft.engine.dsl.process.DataContainer) : Operation {
        return OperationBuilder().createNewInstance()
                .createSetDataOperation(elementToUpdate, with?.build(dataContainer))
    }
}