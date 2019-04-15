package org.bonitasoft.engine.dsl.process

@ProcessDSLMarker
class ParameterContainer {

    private val parameters : MutableMap<String,String> = mutableMapOf()

    fun export(exportedProcessConfiguration: ExportedProcessConfiguration) {
        exportedProcessConfiguration.parameters = parameters
    }

    infix fun String.to(value : String){
        parameters.put(this, value)
    }

}
