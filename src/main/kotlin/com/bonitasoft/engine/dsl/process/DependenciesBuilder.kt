package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression
import org.bonitasoft.engine.expression.ExpressionBuilder


class DependenciesBuilder(private var expressions : MutableList<Expression> = ArrayList() ) {


    fun dataRef (dependency : String, type : String){
        expressions.add(ExpressionBuilder().createDataExpression(dependency,type))
    }





    internal fun build(): List<Expression> {
        return expressions
    }
}
