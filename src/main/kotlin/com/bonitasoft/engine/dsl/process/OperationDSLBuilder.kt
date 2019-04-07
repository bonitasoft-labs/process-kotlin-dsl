package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.operation.Operation
import org.bonitasoft.engine.operation.OperationBuilder

class OperationDSLBuilder(val elementToUpdate: String) {

    var with: ExpressionDSLBuilder? = null

    fun with(expression: ExpressionDSLBuilder) {
        with = expression
    }

    fun build(dataContainer: DataContainer) : Operation {
        return OperationBuilder().createNewInstance()
                .createSetDataOperation(elementToUpdate, with?.build(dataContainer))
    }
}