package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression

/**
 * @author Danila Mazour
 */
class Condition : ExpressionDSLBuilder() {


    override fun build(dataContainer: DataContainer): Expression {
        val builder = super.initBuilder(dataContainer)
                .setReturnType(java.lang.Boolean::class.java.name)
        return builder.done()
    }
}
