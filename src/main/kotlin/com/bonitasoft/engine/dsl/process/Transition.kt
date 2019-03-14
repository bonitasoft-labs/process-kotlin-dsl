package com.bonitasoft.engine.dsl.process


data class Transition(internal val source: String, internal val target : String, internal var default : Boolean = false, internal var condition : Condition = Condition()) {

    fun isDefault() {
        default = true
    }

    fun condition(init : Condition.() -> Unit) : Transition {
        condition.init()
        return this
    }
}
