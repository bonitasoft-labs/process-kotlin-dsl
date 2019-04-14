package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder

class UserTask(parent: org.bonitasoft.engine.dsl.process.DataContainer, name: String) : org.bonitasoft.engine.dsl.process.Activity(parent, name) {

    var actor: org.bonitasoft.engine.dsl.process.ActorRef? = null



    override fun buildFlowNode(builder: ProcessDefinitionBuilder): ActivityDefinitionBuilder {
        val userTaskBuilder: UserTaskDefinitionBuilder = builder.addUserTask(name, actor?.name)
        contract?.build(userTaskBuilder, this)
        return userTaskBuilder
    }

    fun contract(init: org.bonitasoft.engine.dsl.process.ContractContainer.() -> Unit) {
        contract = org.bonitasoft.engine.dsl.process.ContractContainer().apply(init)
    }
}