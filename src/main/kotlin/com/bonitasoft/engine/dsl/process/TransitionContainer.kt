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

    infix fun String.to(that: String): Transition {
        return from(this).to(that)
    }

    infix fun FlowNode.to(that: FlowNode): Transition {
        return from(this.name).to(that.name)
    }

    internal fun add( transition : Transition) {
       transitionsList.add(transition)
    }
}
