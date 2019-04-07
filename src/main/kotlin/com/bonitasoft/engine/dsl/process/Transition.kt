package com.bonitasoft.engine.dsl.process


data class Transition(internal val dataContainer: DataContainer,
                      internal val source: String,
                      internal val target : String,
                      internal var default : Boolean = false,
                      internal var condition : Condition = Condition(dataContainer)) {

    fun isDefault() {
        default = true
    }

    fun hasCondition() : Boolean {
        return condition.hasCondition()
    }

    fun condition(init : Condition.() -> Unit) : Transition {
        condition.init()
        return this
    }
}
