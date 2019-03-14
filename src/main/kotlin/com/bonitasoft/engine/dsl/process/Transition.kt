package com.bonitasoft.engine.dsl.process


data class Transition(internal val source: String, internal val target : String, internal var default : Boolean = false ) {
    fun isDefault() {
        default = true
    }
}
