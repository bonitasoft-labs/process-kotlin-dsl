package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.process.impl.FlowElementContainerBuilder

@ProcessDSLMarker
open class DataContainer(val parent: org.bonitasoft.engine.dsl.process.DataContainer? = null) {

    var dataList: DataList = DataList(this)
    var connectors: MutableList<org.bonitasoft.engine.dsl.process.ConnectorBuilder> = mutableListOf()
    internal var contract: org.bonitasoft.engine.dsl.process.ContractContainer? = null


    fun data(init: DataList.() -> Unit): DataContainer {
        dataList.init()
        return this
    }


    fun connector(className: String, init: org.bonitasoft.engine.dsl.process.ConnectorBuilder.() -> Unit) = connectors.add(org.bonitasoft.engine.dsl.process.ConnectorBuilder().apply(init))

    fun connector(init: org.bonitasoft.engine.dsl.process.ConnectorBuilder.() -> Unit) = connectors.add(org.bonitasoft.engine.dsl.process.ConnectorBuilder().apply(init))


    internal fun resolveData(name: String): Data {
        return dataList.resolveData(name)
    }
    internal fun resolveContract(name: String): String? {
        return contract?.inputs?.find { it.name == name }?.getJavaType()
    }

    internal fun buildData(builder: FlowElementContainerBuilder, businessArchiveBuilder: BusinessArchiveBuilder) {
        dataList.build(builder)

        connectors.forEach {c->
            c.build(builder, businessArchiveBuilder, this)
        }
    }

}