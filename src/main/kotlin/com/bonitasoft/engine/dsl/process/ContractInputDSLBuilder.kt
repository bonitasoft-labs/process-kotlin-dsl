package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.contract.Type


class ContractInputDSLBuilder(val type:Type) {
    var name: String? = null
    var description: String? = null

    fun getJavaType() : String {
        return when(type) {
            Type.TEXT -> "java.lang.String"
            Type.BOOLEAN ->"java.lang.Boolean"
            Type.LONG ->"java.lang.Long"
            Type.INTEGER -> "java.lang.Integer"
            else -> TODO("type $type not implemented")
        }
    }

}
