package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.actorMapping.Actor
import org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping

@ProcessDSLMarker
data class ActorMapping(private val actor: String) {

    private val entries: MutableList<ActorMappingEntry> = mutableListOf()

    fun group(name: String) = entries.add(ActorMappingEntry(group = name))
    fun user(name: String) = entries.add(ActorMappingEntry(user = name))
    fun role(name: String) = entries.add(ActorMappingEntry(role = name))

    fun membership(groupName: String, roleName: String) = entries.add(ActorMappingEntry(group = groupName, role = roleName))

    fun export(exportedActorMapping: ActorMapping) {
        val exportedActor = Actor(actor)
        entries.forEach {
            it.export(exportedActor)
        }
        exportedActorMapping.addActor(exportedActor)
    }
}