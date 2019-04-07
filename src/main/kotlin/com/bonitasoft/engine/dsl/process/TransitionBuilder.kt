package com.bonitasoft.engine.dsl.process

/**
 * @author Danila Mazour
 */
data class TransitionBuilder(val source : String, val transitionContainer: TransitionContainer) {

    fun to(target: String) : Transition {
        val theNewTransition = Transition(transitionContainer.dataContainer, source, target)
        transitionContainer.add(theNewTransition)
        return theNewTransition
    }
}
