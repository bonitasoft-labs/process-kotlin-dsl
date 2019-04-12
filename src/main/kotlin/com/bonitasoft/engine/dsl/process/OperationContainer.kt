package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder

open class OperationContainer {

    val operations : MutableList<OperationDSLBuilder> = mutableListOf()

    fun update(elementToUpdate: String) = OperationDSLBuilder(elementToUpdate).apply { operations.add(this) }


//    infix fun String.takes(that: ExpressionDSLBuilder) {
//        OperationDSLBuilder(this).apply { operations.add(that) }
//    }
    infix fun OperationContainer.update(elementToUpdate: String) = OperationDSLBuilder(elementToUpdate).apply { operations.add(this) }

    fun build(builder: ActivityDefinitionBuilder, dataContainer: DataContainer) {
        operations.forEach { op ->
            builder.addOperation(op.build(dataContainer))
        }
    }


}
