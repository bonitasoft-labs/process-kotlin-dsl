package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.process.impl.FlowElementContainerBuilder

@org.bonitasoft.engine.dsl.process.ProcessDSLMarker
open class DataContainer(val parent: org.bonitasoft.engine.dsl.process.DataContainer? = null) {

    var dataList: MutableList<org.bonitasoft.engine.dsl.process.Data> = mutableListOf()
    var connectors: MutableList<org.bonitasoft.engine.dsl.process.ConnectorBuilder> = mutableListOf()
    internal var contract: org.bonitasoft.engine.dsl.process.ContractContainer? = null

    fun connector(className: String, init: org.bonitasoft.engine.dsl.process.ConnectorBuilder.() -> Unit) = connectors.add(org.bonitasoft.engine.dsl.process.ConnectorBuilder().apply(init))

    fun connector(init: org.bonitasoft.engine.dsl.process.ConnectorBuilder.() -> Unit) = connectors.add(org.bonitasoft.engine.dsl.process.ConnectorBuilder().apply(init))




    fun data(init: org.bonitasoft.engine.dsl.process.Data.() -> Unit): org.bonitasoft.engine.dsl.process.DataContainer {
        val data = org.bonitasoft.engine.dsl.process.Data(this)

        data.init()
        dataList.add(data)
        return this
    }

    internal fun resolveData(name: String): org.bonitasoft.engine.dsl.process.Data {
        return dataList.find { it.name == name } ?: parent?.resolveData(name)
        ?: throw IllegalArgumentException("Dependency named $name not found")
    }
    internal fun resolveContract(name: String): String? {
        return contract?.inputs?.find { it.name == name }?.getJavaType()
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