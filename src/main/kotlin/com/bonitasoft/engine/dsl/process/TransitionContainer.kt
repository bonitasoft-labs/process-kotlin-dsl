package com.bonitasoft.engine.dsl.process

/**
 * @author Danila Mazour
 */
data class TransitionContainer(val dataContainer: DataContainer, val transitionsList: MutableList<Transition> = ArrayList()) {

    fun from(sourceFlowNode: String, init: TransitionBuilder.() -> Unit = {}): TransitionBuilder{
        val transitionBuilder = TransitionBuilder(sourceFlowNode,this)
        transitionBuilder.init()
        return transitionBuilder
    }

    internal fun add( transition : Transition) {
       transitionsList.add(transition)
    }
}
