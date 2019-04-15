package org.bonitasoft.engine.dsl.process


@ProcessDSLMarker
class ActorMappingContainer {

    private val actorMappings: MutableList<ActorMapping> = mutableListOf()

    infix fun String.toGroup(groupName: String) {
        actorMappings.add(ActorMapping(actor = this).apply { group(groupName) })
    }

    infix fun String.toUser(userName: String) {
        actorMappings.add(ActorMapping(actor = this).apply { user(userName) })
    }

    infix fun String.toRole(roleName: String) {
        actorMappings.add(ActorMapping(actor = this).apply { role(roleName) })
    }

    infix fun String.to(init: ActorMapping.() -> Unit) {
        val actorMapping = ActorMapping(actor = this)
        actorMapping.init()
        actorMappings.add(actorMapping)
    }

    fun export(exportedProcessConfiguration: ExportedProcessConfiguration) {
        val exportedActorMapping = org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping()

        actorMappings.forEach {
            it.export(exportedActorMapping)
        }
        exportedProcessConfiguration.addActorMapping(exportedActorMapping)
    }
}
