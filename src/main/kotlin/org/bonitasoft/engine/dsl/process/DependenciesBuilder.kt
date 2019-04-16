package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression
import org.bonitasoft.engine.expression.ExpressionBuilder


@ProcessDSLMarker
class DependenciesBuilder {

    private var dataRef: MutableList<String> = mutableListOf()
    private var expressionBuilders: MutableList<ExpressionDSLBuilder> = mutableListOf()

    fun dataRef(dependency: String) {
        dataRef.add(dependency)
    }
    fun dependency(dependency: ExpressionDSLBuilder) {
        expressionBuilders.add(dependency)
    }

    internal fun build(dataContainer: DataContainer): List<Expression> {
        val expressions = dataRef.map { name ->
            val dep = dataContainer.resolveData(name)
            ExpressionBuilder().createDataExpression(name, dep.type.type)
        }.toMutableList()
        expressions.addAll(expressionBuilders.map { it.build(dataContainer) })
        return expressions
    }
}
