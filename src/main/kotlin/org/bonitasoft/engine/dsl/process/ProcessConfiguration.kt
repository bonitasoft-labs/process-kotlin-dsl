package org.bonitasoft.engine.dsl.process


@ProcessDSLMarker
class ProcessConfiguration {

    private var actorMappingContainer: ActorMappingContainer? = null


    fun actorMapping(init: ActorMappingContainer.() -> Unit) {
        actorMappingContainer = ActorMappingContainer()
        actorMappingContainer?.init()
    }

    public fun export(): ExportedProcessConfiguration {
        val exportedProcessConfiguration = ExportedProcessConfiguration()
        actorMappingContainer?.export(exportedProcessConfiguration)
        return exportedProcessConfiguration
    }

}