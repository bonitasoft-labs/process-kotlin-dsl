package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression

@org.bonitasoft.engine.dsl.process.ProcessDSLMarker
data class Data(val dataContainer: org.bonitasoft.engine.dsl.process.DataContainer, var name: String = "", var type: org.bonitasoft.engine.dsl.process.DataType = org.bonitasoft.engine.dsl.process.DataType.Companion.string(), var initialExpression: org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder? = null) {

    fun initialValue(init: org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder.() -> Unit) {
        initialExpression = org.bonitasoft.engine.dsl.process.ExpressionDSLBuilder().apply(init)
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
        fun string(): org.bonitasoft.engine.dsl.process.DataType {
            return org.bonitasoft.engine.dsl.process.DataType("java.lang.String")
        }
        fun boolean(): org.bonitasoft.engine.dsl.process.DataType {
            return org.bonitasoft.engine.dsl.process.DataType("java.lang.Boolean")
        }
        fun integer(): org.bonitasoft.engine.dsl.process.DataType {
            return org.bonitasoft.engine.dsl.process.DataType("java.lang.Integer")
        }

        fun custom(type: String): org.bonitasoft.engine.dsl.process.DataType {
            return org.bonitasoft.engine.dsl.process.DataType(type)
        }
    }


}
