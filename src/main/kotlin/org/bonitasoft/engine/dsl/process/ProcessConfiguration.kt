package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchive
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder


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
    fun contributeToBAR(bar: BusinessArchiveBuilder, processDefinitionBuilder: ProcessDefinitionBuilder) {
        actorMappingContainer?.build(bar)
        parameterContainer?.build(bar, processDefinitionBuilder)
    }


}