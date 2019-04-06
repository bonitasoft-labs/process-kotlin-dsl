package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression
import org.bonitasoft.engine.expression.ExpressionBuilder
import java.util.*

/**
 * @author Danila Mazour
 */
class Condition(var noCondition : Boolean = true, var expressionBuilder : ExpressionBuilder = ExpressionBuilder()) {

    fun constant(condition: Boolean) {
        noCondition = false
        expressionBuilder.createConstantBooleanExpression(condition)
    }

    fun groovy(script : String){
        noCondition = false
        expressionBuilder.createGroovyScriptExpression(UUID.randomUUID().toString(),script,"java.lang.Boolean")
    }

    fun groovy(script : String, init : DependenciesBuilder.() -> Unit) {
        noCondition = false
        var dependenciesBuilder = DependenciesBuilder()
        dependenciesBuilder.init()
        expressionBuilder.createGroovyScriptExpression(UUID.randomUUID().toString(),script,"java.lang.Boolean", dependenciesBuilder.build())
    }

    fun dataRef (data : String) {
        noCondition = false
        expressionBuilder.createDataExpression(data,"java.lang.Boolean")
    }
    internal fun build(): Expression {
        return expressionBuilder.done()
    }
}
