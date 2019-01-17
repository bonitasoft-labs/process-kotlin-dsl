package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder

abstract class FlowNode(val name: String) {
    val outgoingTransitions: MutableList<Transition> = ArrayList()

    fun transition(next: String) {
        outgoingTransitions.add(Transition(next))
    }
    fun transition(next: String, init: Transition.() -> Unit) {
        val transition = Transition(next)
        transition.init()
        outgoingTransitions.add(transition)
    }

    abstract fun build(builder: ProcessDefinitionBuilder)

}
