package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression

data class Data(val dataContainer: DataContainer, var name: String = "", var type: DataType = DataType.string(), var initialExpression: ExpressionDSLBuilder? = null) {

    fun initialValue(init: ExpressionDSLBuilder.() -> Unit) {
        initialExpression = ExpressionDSLBuilder().apply(init)
    }

    internal fun getDataType() : String{
        return type.type
    }

    internal fun getInitialValue(): Expression? {
        return initialExpression?.build(dataContainer)
    }
}

class DataType(val type: String) {

    companion object {
        fun string(): DataType {
            return DataType("java.lang.String")
        }
        fun boolean(): DataType {
            return DataType("java.lang.Boolean")
        }

        fun custom(type: String): DataType {
            return DataType(type)
        }
    }


}
