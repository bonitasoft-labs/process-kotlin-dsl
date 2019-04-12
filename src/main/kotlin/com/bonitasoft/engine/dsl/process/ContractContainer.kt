package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.contract.Type
import org.bonitasoft.engine.bpm.process.impl.ContractDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder

@ProcessMarker
class ContractContainer {

    val inputs: MutableList<ContractInputDSLBuilder> = mutableListOf()


    val text: ContractInputDSLBuilder
        get() = create(Type.TEXT)
    val integer: ContractInputDSLBuilder
        get() = create(Type.INTEGER)
    val long: ContractInputDSLBuilder
        get() = create(Type.LONG)
    val boolean: ContractInputDSLBuilder
        get() = create(Type.BOOLEAN)

    infix fun ContractInputDSLBuilder.named(name: String): ContractInputDSLBuilder {
        this.name = name
        return this
    }

    infix fun ContractInputDSLBuilder.withDescription(description: String): ContractInputDSLBuilder {
        this.description = description
        return this
    }


    private fun create(type: Type): ContractInputDSLBuilder {
        val input = ContractInputDSLBuilder(type)
        inputs.add(input)
        return input
    }

    fun build(builder: ProcessDefinitionBuilder, dataContainer: DataContainer) {
        val contractBuilder = builder.addContract()
        build(contractBuilder, dataContainer)
    }

    fun build(builder: UserTaskDefinitionBuilder, dataContainer: DataContainer) {
        val contractBuilder = builder.addContract()
        build(contractBuilder, dataContainer)
    }

    private fun build(builder: ContractDefinitionBuilder, dataContainer: DataContainer) {
        inputs.forEach { input ->
            builder.addInput(input.name, input.type, input.description)
        }
    }

}