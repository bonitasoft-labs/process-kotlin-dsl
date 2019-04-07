package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression
import org.bonitasoft.engine.expression.ExpressionBuilder


class DependenciesBuilder(private val dataContainer: DataContainer, private var expressions: MutableList<String> = ArrayList()) {

    fun dataRef(dependency: String) {
        expressions.add(dependency)
    }

    internal fun build(): List<Expression> {
        return expressions.map { name ->
            val dep = dataContainer.resolveData(name) ?: throw IllegalArgumentException("Dependency named $name not found")
            ExpressionBuilder().createDataExpression(name, dep.name)
        }
    }
}
