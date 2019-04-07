package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.process.impl.FlowElementContainerBuilder

open class DataContainer(val parent: DataContainer? = null) {

    var dataList: MutableList<Data> = mutableListOf()
    var connectors: MutableList<ConnectorBuilder> = mutableListOf()

    fun connector(className: String, init: ConnectorBuilder.() -> Unit) = connectors.add(ConnectorBuilder().apply(init))

    fun connector(init: ConnectorBuilder.() -> Unit) = connectors.add(ConnectorBuilder().apply(init))




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

    internal fun buildData(builder: FlowElementContainerBuilder, businessArchiveBuilder: BusinessArchiveBuilder) {
        dataList.forEach {
            builder.addData(it.name, it.getDataType(), it.getInitialValue())
        }
        connectors.forEach {c->
            c.build(builder, businessArchiveBuilder, this)
        }
    }

}