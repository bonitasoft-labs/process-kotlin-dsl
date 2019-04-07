package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression
import org.bonitasoft.engine.expression.ExpressionBuilder
import org.bonitasoft.engine.expression.ExpressionInterpreter
import org.bonitasoft.engine.expression.ExpressionType
import java.util.*

/**
 * @author Danila Mazour
 */
class Condition(dataContainer: DataContainer) : ExpressionDSLBuilder(dataContainer) {


    override fun build(): Expression {
        val builder = super.initBuilder()
                .setReturnType(java.lang.Boolean::class.java.name)
        return builder.done()
    }
}
