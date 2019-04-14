package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder

@ProcessDSLMarker
open class OperationContainer {

    val operations : MutableList<OperationDSLBuilder> = mutableListOf()

    fun update(elementToUpdate: String): OperationDSLBuilder {
        val operationDSLBuilder = OperationDSLBuilder(elementToUpdate)
        operations.add(operationDSLBuilder)
        return operationDSLBuilder
    }


//    infix fun String.takes(that: ExpressionDSLBuilder) {
//        OperationDSLBuilder(this).apply { operations.add(that) }
//    }
    infix fun OperationContainer.update(elementToUpdate: String): OperationDSLBuilder {
    val operationDSLBuilder = OperationDSLBuilder(elementToUpdate)
    operations.add(operationDSLBuilder)
    return operationDSLBuilder
    }

    fun build(builder: ActivityDefinitionBuilder, dataContainer: org.bonitasoft.engine.dsl.process.DataContainer) {
        operations.forEach { op ->
            builder.addOperation(op.build(dataContainer))
        }
    }


}
