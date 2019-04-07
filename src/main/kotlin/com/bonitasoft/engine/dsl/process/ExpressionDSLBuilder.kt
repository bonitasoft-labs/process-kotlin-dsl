package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression
import org.bonitasoft.engine.expression.ExpressionBuilder
import org.bonitasoft.engine.expression.ExpressionType
import java.util.*

class ExpressionDSLBuilder(val dataContainer: DataContainer, var expressionBuilder: ExpressionBuilder = ExpressionBuilder().createNewInstance(UUID.randomUUID().toString())) {

    fun constant(condition: Boolean) {
        setConstant(condition).setReturnType("java.lang.Boolean")
    }

    fun constant(condition: String) {
        setConstant(condition).setReturnType("java.lang.String")
    }

    private fun setConstant(condition: Any) =
            expressionBuilder.setExpressionType(ExpressionType.TYPE_CONSTANT).setContent(condition.toString())

    fun groovy(script: String, type: String) {
        expressionBuilder.createGroovyScriptExpression(UUID.randomUUID().toString(), script, type)
    }

    fun groovy(script: String, type: String, init: DependenciesBuilder.() -> Unit) {
        var dependenciesBuilder = DependenciesBuilder(dataContainer)
        dependenciesBuilder.init()
        expressionBuilder.createGroovyScriptExpression(UUID.randomUUID().toString(), script, type, dependenciesBuilder.build())
    }

    fun dataRef(data: String, type: String) {
        expressionBuilder.createDataExpression(data, type)
    }

    internal fun build(): Expression {
        return expressionBuilder.done()
    }
}
