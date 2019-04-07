package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression
import org.bonitasoft.engine.expression.ExpressionBuilder
import org.bonitasoft.engine.expression.ExpressionInterpreter
import org.bonitasoft.engine.expression.ExpressionType
import java.util.*

/**
 * @author Danila Mazour
 */
class Condition(val dataContainer: DataContainer) {

    private var type: ExpressionType? = null
    private var interpreter: String? = null
    private var content: String? = null
    private var dependenciesBuilder: DependenciesBuilder? = null


    fun constant(condition: Boolean) {
        type = ExpressionType.TYPE_CONSTANT
        content = condition.toString()
    }

    fun groovy(script : String){
        type = ExpressionType.TYPE_READ_ONLY_SCRIPT
        interpreter = ExpressionInterpreter.GROOVY.name
        content = script
    }

    fun groovy(script : String, init : DependenciesBuilder.() -> Unit) {
        groovy(script)
        dependenciesBuilder = DependenciesBuilder(dataContainer)
        dependenciesBuilder?.init()
    }

    fun dataRef (data : String) {
        type = ExpressionType.TYPE_VARIABLE
        content = data
    }
    internal fun build(): Expression {

        val builder = ExpressionBuilder().createNewInstance(UUID.randomUUID().toString())
                .setReturnType(java.lang.Boolean::class.java.name)
                .setContent(content)
                .setExpressionType(type)
                .setInterpreter(interpreter)
        dependenciesBuilder?.build().apply { builder.setDependencies(this) }
        return builder.done()
    }

    fun hasCondition() : Boolean {
        return content != null
    }
}
