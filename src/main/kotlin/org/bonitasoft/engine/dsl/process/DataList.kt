package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.FlowElementContainerBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder


@ProcessDSLMarker
class DataList(val parent: DataContainer) {


    internal var dataList: MutableList<Data> = mutableListOf()

    val boolean: Data
        get() = data(DataType.boolean())
    val text: Data
        get() = data(DataType.string())
    val integer: Data
        get() = data(DataType.integer())

    fun custom(type: String): Data {
        return data(DataType.custom(type))
    }

    fun data(type: DataType): Data {
        val data = Data(parent, type = type)
        dataList.add(data)
        return data
    }


    infix fun Data.named(name: String): Data {
        this.name = name
        return this
    }

    infix fun Data.withInitialValue(init: ExpressionDSLBuilder.() -> Unit): Data {
        this.initialExpression = ExpressionDSLBuilder().apply(init)
        return this
    }

    infix fun Data.withInitialValue(expression: ExpressionDSLBuilder): Data {
        this.initialExpression = expression
        return this
    }

    internal fun resolveData(name: String): Data {
        return dataList.find { it.name == name } ?: parent?.parent?.resolveData(name)
        ?: throw IllegalArgumentException("Dependency named $name not found")
    }

    fun build(builder: FlowElementContainerBuilder) {
        dataList.forEach {
            builder.addData(it.name, it.getDataType(), it.getInitialValue())
        }
    }

    fun build(builder: ProcessDefinitionBuilder) {
        dataList.forEach {
            builder.addData(it.name, it.getDataType(), it.getInitialValue())
        }
    }
}