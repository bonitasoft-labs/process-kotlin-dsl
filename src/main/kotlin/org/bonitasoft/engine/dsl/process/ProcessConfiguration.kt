package org.bonitasoft.engine.dsl.process


@ProcessDSLMarker
class ProcessConfiguration {

    private var actorMappingContainer: ActorMappingContainer? = null
    private var parameterContainer: ParameterContainer? = null


    fun actorMapping(init: ActorMappingContainer.() -> Unit) {
        actorMappingContainer = ActorMappingContainer().apply(init)
    }

    fun parameters(init: ParameterContainer.() -> Unit) {
        parameterContainer = ParameterContainer().apply(init)
    }

    fun export(): ExportedProcessConfiguration {
        val exportedProcessConfiguration = ExportedProcessConfiguration()
        actorMappingContainer?.export(exportedProcessConfiguration)
        parameterContainer?.export(exportedProcessConfiguration)
        return exportedProcessConfiguration
    }

}