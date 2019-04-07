package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression
import org.bonitasoft.engine.expression.ExpressionBuilder


class DependenciesBuilder(private var expressions: MutableList<String> = ArrayList()) {

    fun dataRef(dependency: String) {
        expressions.add(dependency)
    }

    internal fun build(dataContainer: DataContainer): List<Expression> {
        return expressions.map { name ->
            val dep = dataContainer.resolveData(name)
            ExpressionBuilder().createDataExpression(name, dep.name)
        }
    }
}
