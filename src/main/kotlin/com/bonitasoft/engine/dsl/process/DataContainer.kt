package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.FlowElementContainerBuilder

open class DataContainer(val parent: DataContainer? = null, var dataList: MutableList<Data> = ArrayList()) {

    fun data(init: Data.() -> Unit): DataContainer {
        val data = Data(this)

        data.init()
        dataList.add(data)
        return this
    }

    internal fun resolveData(name: String): Data {
        return dataList.find { it.name == name } ?: parent?.resolveData(name)
        ?: throw IllegalArgumentException("Dependency named $name not found")
    }

    internal fun buildData(builder: FlowElementContainerBuilder) {
        dataList.forEach {
            builder.addData(it.name, it.getDataType(), it.getInitialValue())
        }
    }

}