package org.bonitasoft.engine.dsl.process

class ExportedProcessConfiguration {

    var actorMapping: org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping? = null
    var parameters: Map<String, String>? = null


    fun addActorMapping(actorMapping: org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping) {
        this.actorMapping = actorMapping
    }


}
