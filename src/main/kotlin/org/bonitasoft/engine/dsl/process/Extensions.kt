package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression
import org.bonitasoft.engine.expression.ExpressionBuilder


fun String.toExpression(): Expression {
    return ExpressionBuilder().createConstantStringExpression(this)
}
fun Boolean.toExpression(): Expression {
    return ExpressionBuilder().createConstantBooleanExpression(this)
}
fun Int.toExpression(): Expression {
    return ExpressionBuilder().createConstantIntegerExpression(this)
}
fun Float.toExpression(): Expression {
    return ExpressionBuilder().createConstantFloatExpression(this)
}
fun Long.toExpression(): Expression {
    return ExpressionBuilder().createConstantLongExpression(this)
}