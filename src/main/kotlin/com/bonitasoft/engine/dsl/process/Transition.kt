package com.bonitasoft.engine.dsl.process


data class Transition(internal val source: String,
                      internal val target : String,
                      internal var default : Boolean = false,
                      internal var condition: Condition? = null) {

    fun isDefault() {
        default = true
    }

    fun hasCondition(): Boolean = condition != null

    fun condition(init : Condition.() -> Unit) : Transition {
        condition = Condition().apply(init)
        return this
    }
}
