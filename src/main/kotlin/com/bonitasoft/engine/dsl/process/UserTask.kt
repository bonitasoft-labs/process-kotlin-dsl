package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder

class UserTask(parent: DataContainer, name: String) : Activity(parent, name) {

    var actor: ActorRef? = null



    override fun buildFlowNode(builder: ProcessDefinitionBuilder): ActivityDefinitionBuilder {
        val userTaskBuilder: UserTaskDefinitionBuilder = builder.addUserTask(name, actor?.name)
        contract?.build(userTaskBuilder, this)
        return userTaskBuilder
    }

    fun contract(init: ContractContainer.() -> Unit) {
        contract = ContractContainer().apply(init)
    }
}