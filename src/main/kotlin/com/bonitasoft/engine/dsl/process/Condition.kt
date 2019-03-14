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

    internal fun toExpression(): Expression {
        return expressionBuilder.done()
    }
}
