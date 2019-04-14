package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.contract.Type
import org.bonitasoft.engine.bpm.process.impl.ContractDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder

@org.bonitasoft.engine.dsl.process.ProcessDSLMarker
class ContractContainer {

    val inputs: MutableList<org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder> = mutableListOf()


    val text: org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder
        get() = create(Type.TEXT)
    val integer: org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder
        get() = create(Type.INTEGER)
    val long: org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder
        get() = create(Type.LONG)
    val boolean: org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder
        get() = create(Type.BOOLEAN)

    infix fun org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder.named(name: String): org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder {
        this.name = name
        return this
    }

    infix fun org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder.withDescription(description: String): org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder {
        this.description = description
        return this
    }


    private fun create(type: Type): org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder {
        val input = org.bonitasoft.engine.dsl.process.ContractInputDSLBuilder(type)
        inputs.add(input)
        return input
    }

    fun build(builder: ProcessDefinitionBuilder, dataContainer: org.bonitasoft.engine.dsl.process.DataContainer) {
        val contractBuilder = builder.addContract()
        build(contractBuilder, dataContainer)
    }

    fun build(builder: UserTaskDefinitionBuilder, dataContainer: org.bonitasoft.engine.dsl.process.DataContainer) {
        val contractBuilder = builder.addContract()
        build(contractBuilder, dataContainer)
    }

    private fun build(builder: ContractDefinitionBuilder, dataContainer: org.bonitasoft.engine.dsl.process.DataContainer) {
        inputs.forEach { input ->
            builder.addInput(input.name, input.type, input.description)
        }
    }

}