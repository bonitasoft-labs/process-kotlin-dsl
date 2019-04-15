package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchive
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder


@DslMarker
annotation class ProcessDSLMarker

class Process(private val name: String,
                   private val version: String,
                   private val flowNodes: MutableList<FlowNode> = ArrayList()) : org.bonitasoft.engine.dsl.process.DataContainer() {

    private val transitionContainer: TransitionContainer = TransitionContainer(this)
    private var initiator : String? = null
    private var actors: MutableList<String> = mutableListOf()

    fun start(name: String, init: StartEvent.() -> Unit = {}) = flowNode(StartEvent(this, name), init)
    fun startMessage(name: String, init: StartMessageEvent.() -> Unit = {}) = flowNode(StartMessageEvent(this, name), init)
    fun catchMessage(name: String, init: org.bonitasoft.engine.dsl.process.CatchMessageEvent.() -> Unit = {}) = flowNode(org.bonitasoft.engine.dsl.process.CatchMessageEvent(this, name), init)
    fun throwMessage(name: String, init: ThrowMessageEvent.() -> Unit = {}) = flowNode(ThrowMessageEvent(this, name), init)
    fun automaticTask(name: String, init: org.bonitasoft.engine.dsl.process.AutomaticTask.() -> Unit = {}) = flowNode(org.bonitasoft.engine.dsl.process.AutomaticTask(this, name), init)
    fun userTask(name: String, init: UserTask.() -> Unit = {}) = flowNode(UserTask(this, name), init)
    fun parallelGateway(name: String, init: ParallelGateway.() -> Unit= {}) = flowNode(ParallelGateway(this, name), init)
    fun inclusiveGateway(name: String, init: InclusiveGateway.() -> Unit = {}) = flowNode(InclusiveGateway(this, name), init)
    fun exclusiveGateway(name: String, init: ExclusiveGateway.() -> Unit = {}) = flowNode(ExclusiveGateway(this, name), init)
    fun transitions(init: TransitionContainer.() -> Unit) {
        transitionContainer.init()
    }

    fun <T : FlowNode> flowNode(task: T, init: T.() -> Unit): T {
        task.init()
        flowNodes.add(task)
        return task
    }

    fun export(): BusinessArchive {

        val businessArchiveBuilder = BusinessArchiveBuilder().createNewBusinessArchive()


        val builder = ProcessDefinitionBuilder().createNewInstance(name, version)
        if (initiator != null) {
            builder.addActor(initiator, true)
        }
        actors.forEach { actor ->
            builder.addActor(actor)
        }

        flowNodes.forEach { task ->
            task.build(builder, businessArchiveBuilder)
        }

        transitionContainer.transitionsList.forEach{ transition ->
            transition.build(builder,this)
        }
        dataList.forEach {
            builder.addData(it.name, it.getDataType(), it.getInitialValue())
        }

        contract?.build(builder,this)

        builder.done()
        businessArchiveBuilder.setProcessDefinition(builder.done())
        //FIXME add kotlin runtime
        return businessArchiveBuilder.done()
    }

    fun initiator(name: String): org.bonitasoft.engine.dsl.process.ActorRef {
        this.initiator = name
        return org.bonitasoft.engine.dsl.process.ActorRef(name)
    }

    fun actor(name: String): org.bonitasoft.engine.dsl.process.ActorRef {
        this.actors.add(name)
        return org.bonitasoft.engine.dsl.process.ActorRef(name)
    }

    fun contract(init: org.bonitasoft.engine.dsl.process.ContractContainer.() -> Unit) {
        contract = org.bonitasoft.engine.dsl.process.ContractContainer().apply(init)
    }
}
