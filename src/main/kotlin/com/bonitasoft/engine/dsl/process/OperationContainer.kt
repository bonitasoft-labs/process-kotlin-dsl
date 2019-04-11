package com.bonitasoft.engine.dsl.process

open class OperationContainer {

    val operations : MutableList<OperationDSLBuilder> = mutableListOf()

    fun update(elementToUpdate: String) = OperationDSLBuilder(elementToUpdate).apply { operations.add(this) }


//    infix fun String.takes(that: ExpressionDSLBuilder) {
//        OperationDSLBuilder(this).apply { operations.add(that) }
//    }
    infix fun OperationContainer.update(elementToUpdate: String) = OperationDSLBuilder(elementToUpdate).apply { operations.add(this) }


}
