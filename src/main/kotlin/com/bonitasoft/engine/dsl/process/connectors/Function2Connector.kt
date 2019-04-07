package com.bonitasoft.engine.dsl.process.connectors;

import org.bonitasoft.engine.connector.AbstractConnector

open class Function2Connector : AbstractConnector() {
    override fun validateInputParameters() {
    }

    override fun executeBusinessLogic() {
        val functionClassName: String = getInputParameter("functionClassName") as String
        val functionParam1: Any = getInputParameter("functionParam1")
        val functionParam2: Any = getInputParameter("functionParam2")
        val clazz = Class.forName(functionClassName)
        val method = clazz.declaredMethods.find { it.name == "invoke" }
        setOutputParameter("result", method?.invoke(clazz.newInstance(), functionParam1, functionParam2))
    }


}
