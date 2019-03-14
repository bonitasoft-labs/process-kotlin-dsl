package com.bonitasoft.engine.dsl.process

data class TransitionContainer(val transitionsList: MutableList<Transition> = ArrayList()) {

    fun from(sourceFlowNode: String) : TransitionBuilder{
        return TransitionBuilder(sourceFlowNode, this)
    }

    fun from(sourceFlowNode: String, init: TransitionBuilder.() -> Unit): TransitionBuilder{
        val transitionBuilder = TransitionBuilder(sourceFlowNode,this)
        transitionBuilder.init()
        return transitionBuilder
    }

    internal fun add( transition : Transition) {
       transitionsList.add(transition)
    }
}
