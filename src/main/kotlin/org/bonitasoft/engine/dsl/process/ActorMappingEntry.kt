package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.actorMapping.Actor

data class ActorMappingEntry(
        private var user: String? = null,
        private var group: String? = null,
        private var role: String? = null) {

    fun export(exportedActor: Actor) {
        if (group != null && role != null) {
            exportedActor.addMembership(group, role)
        } else if (group != null) {
            exportedActor.addGroup(group)
        } else if (role != null) {
            exportedActor.addRole(role)
        } else if (user != null) {
            exportedActor.addUser(user)
        }
    }

}