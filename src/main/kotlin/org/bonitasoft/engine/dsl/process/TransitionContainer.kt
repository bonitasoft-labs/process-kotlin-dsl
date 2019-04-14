package org.bonitasoft.engine.dsl.process

/**
 * @author Danila Mazour
 */
data class TransitionContainer(val dataContainer: org.bonitasoft.engine.dsl.process.DataContainer, val transitionsList: MutableList<TransitionBuilder> = ArrayList()) {

    val default: TransitionBuilder
        get() = TransitionBuilder(transitionContainer = this).apply { transitionsList.add(this) }

    fun from(sourceFlowNode: String, init: TransitionBuilder.() -> Unit = {}): TransitionBuilder{
        val transitionBuilder = TransitionBuilder(sourceFlowNode, transitionContainer = this).apply(init)
        transitionsList.add(transitionBuilder)
        return transitionBuilder
    }

    infix fun String.to(that: String): TransitionBuilder {
        return from(this).to(that)
    }

    infix fun TransitionBuilder.from(that: String): TransitionBuilder {
        return this.from(that)
    }

    infix fun TransitionBuilder.from(that: FlowNode): TransitionBuilder {
        return this.from(that)
    }

    infix fun FlowNode.to(that: FlowNode): TransitionBuilder {
        return from(this.name).to(that.name)
    }

    infix fun TransitionBuilder.to(that: FlowNode): TransitionBuilder {
        return this.to(that.name)
    }

    infix fun TransitionBuilder.to(that: String): TransitionBuilder {
        return this.to(that)
    }

    infix fun TransitionBuilder.withCondition(that: ExpressionDSLBuilder): TransitionBuilder {
        this.condition = that
        return this
    }

}
