package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchive

object BARBuilder {

    fun build(process: Process, processConfiguration: ProcessConfiguration): BusinessArchive {
        val (businessArchiveBuilder, processDefinitionBuilder) = process.build()
        processConfiguration.contributeToBAR(businessArchiveBuilder, processDefinitionBuilder)
        val processDef = processDefinitionBuilder.done()
        return businessArchiveBuilder.setProcessDefinition(processDef).done()
    }
}