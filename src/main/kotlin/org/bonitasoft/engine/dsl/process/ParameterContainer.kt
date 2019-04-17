package org.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder
import java.io.Serializable

@ProcessDSLMarker
class ParameterContainer {

    private val parameters : MutableMap<String,Serializable> = mutableMapOf()

    infix fun String.to(value : Int){
        parameters[this] = value
    }
    infix fun String.to(value : String){
        parameters[this] = value
    }

    fun build(businessArchiveBuilder: BusinessArchiveBuilder, processDefinitionBuilder: ProcessDefinitionBuilder) {
        parameters.forEach { key, value ->
            processDefinitionBuilder.addParameter(key,
                    when(value){
                        is Int -> "java.lang.Long"
                        else -> "java.lang.String"
                    })

        }

        businessArchiveBuilder.setParameters(parameters.mapValues { (_,v)->v.toString() })
    }

}
