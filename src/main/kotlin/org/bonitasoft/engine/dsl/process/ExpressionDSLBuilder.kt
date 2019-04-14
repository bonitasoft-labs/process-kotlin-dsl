package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.*
import java.util.*

@ProcessDSLMarker
open class ExpressionDSLBuilder {

    object ExpressionDSLBuilderObject {


        fun constant(data: String): ExpressionDSLBuilder = ExpressionDSLBuilder().apply { constant(data) }
        fun constant(data: Boolean): ExpressionDSLBuilder = ExpressionDSLBuilder().apply { constant(data) }
        fun dataRef(data: String): ExpressionDSLBuilder = ExpressionDSLBuilder().apply { dataRef(data) }
        fun groovy(script: String): ExpressionDSLBuilder = ExpressionDSLBuilder().apply { groovy(script) }
        fun groovy(script: String, init: org.bonitasoft.engine.dsl.process.DependenciesBuilder.() -> Unit): ExpressionDSLBuilder = ExpressionDSLBuilder().apply { groovy(script, init) }
        fun input(name: String, type: String): ExpressionDSLBuilder = ExpressionDSLBuilder().apply { input(name, type) }
        fun contract(name: String): ExpressionDSLBuilder = ExpressionDSLBuilder().apply { contract(name) }
        val caseId: ExpressionDSLBuilder
            get() = ExpressionDSLBuilder().apply { engineConstant(ExpressionConstants.PROCESS_INSTANCE_ID) }
    }


    private var name: String? = null
    private var type: ExpressionType? = null
    private var interpreter: String? = null
    private var content: String? = null
    private var returnType: String? = null
    private var dependenciesBuilder: org.bonitasoft.engine.dsl.process.DependenciesBuilder? = null

    open fun groovy(script: String) {
        type = ExpressionType.TYPE_READ_ONLY_SCRIPT
        interpreter = ExpressionInterpreter.GROOVY.name
        content = script
    }

    open fun groovy(script: String, init: org.bonitasoft.engine.dsl.process.DependenciesBuilder.() -> Unit) {
        groovy(script)
        dependenciesBuilder = org.bonitasoft.engine.dsl.process.DependenciesBuilder()
        dependenciesBuilder?.init()
    }

    fun dataRef(data: String) {
        name = data
        type = ExpressionType.TYPE_VARIABLE
        content = data
    }

    fun constant(condition: Boolean) {
        type = ExpressionType.TYPE_CONSTANT
        returnType = "java.lang.Boolean"
        content = condition.toString()
    }

    fun constant(condition: String) {
        type = ExpressionType.TYPE_CONSTANT
        returnType = "java.lang.String"
        content = condition
    }

    fun input(name: String) {
        type = ExpressionType.TYPE_INPUT
        returnType = "java.lang.String"
        content = name
    }

    fun input(name: String, type: String) {
        this.type = ExpressionType.TYPE_INPUT
        returnType = type
        content = name
    }


    fun contract(name: String) {
        this.type = ExpressionType.TYPE_CONTRACT_INPUT
        content = name
    }

    fun groovy(script: String, type: String) {
        groovy(script)
        returnType = type
    }

    fun groovy(script: String, type: String, init: org.bonitasoft.engine.dsl.process.DependenciesBuilder.() -> Unit) {
        groovy(script, init)
        returnType = type
    }

    fun engineConstant(value: ExpressionConstants) {
        name = value.engineConstantName
        content = value.engineConstantName
        type = ExpressionType.TYPE_ENGINE_CONSTANT
        returnType = value.returnType
    }

    internal open fun build(dataContainer: org.bonitasoft.engine.dsl.process.DataContainer, returnTypeIfNotSet : String? = "java.lang.String"): Expression {
        if (type == ExpressionType.TYPE_VARIABLE && content != null) {
            returnType = dataContainer.resolveData(content!!).type.type
        }
        if (type == ExpressionType.TYPE_CONTRACT_INPUT && content != null) {
            returnType = dataContainer.resolveContract(content!!)
        }
        if (returnType == null) {
            returnType = returnTypeIfNotSet
        }
        val builder = ExpressionBuilder().createNewInstance(name ?:UUID.randomUUID().toString())
                .setReturnType(returnType)
                .setContent(content)
                .setExpressionType(type)
                .setInterpreter(interpreter)
        dependenciesBuilder?.build(dataContainer).apply { builder.setDependencies(this) }
        return builder.done()
    }

}