package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.bar.BusinessArchiveFactory
import org.bonitasoft.engine.bpm.process.DesignProcessDefinition
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import java.io.File

open class Process(private val name: String,
                   private val version: String,
                   private val flowNodes: MutableList<FlowNode> = ArrayList(),
                   private val transitionContainer: TransitionContainer = TransitionContainer()) : DataContainer() {

    fun automaticTask(name: String, init: FlowNode.() -> Unit = {}) = flowNode(AutomaticTask(name), init)
    fun parallelGateway(name: String, init: FlowNode.() -> Unit= {}) = flowNode(ParallelGateway(name), init)
    fun inclusiveGateway(name: String, init: FlowNode.() -> Unit = {}) = flowNode(InclusiveGateway(name), init)
    fun exclusiveGateway(name: String, init: FlowNode.() -> Unit = {}) = flowNode(ExclusiveGateway(name), init)
    fun transitions(init: TransitionContainer.() -> Unit) {
        transitionContainer.init()
    }

    fun <T : FlowNode> flowNode(task: T, init: T.() -> Unit): T {
        task.init()
        flowNodes.add(task)
        return task
    }
    fun export(): DesignProcessDefinition {
        val builder = ProcessDefinitionBuilder().createNewInstance(name, version)
        flowNodes.forEach { task ->
            task.build(builder)
        }

        transitionContainer.transitionsList.forEach{ transition ->
            if(!transition.default) {
                if (!transition.condition.noCondition) {

                    builder.addTransition(transition.source, transition.target, transition.condition.expressionBuilder.done())
                } else {
                    builder.addTransition(transition.source, transition.target)
                }
            } else{
                builder.addDefaultTransition(transition.source,transition.target)
            }
        }

        dataList.forEach {
            builder.addData(it.name, it.getDataType(), it.getInitialValue())
        }

        return builder.done()
    }


    fun export(file: File){
        val processDefinition = export()
        val businessArchive = BusinessArchiveBuilder().createNewBusinessArchive().setProcessDefinition(processDefinition).done()
        BusinessArchiveFactory.writeBusinessArchiveToFile(businessArchive, file)
    }
}
