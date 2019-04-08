package com.bonitasoft.engine.dsl.process.connectors;

import org.bonitasoft.engine.connector.AbstractConnector
import jdk.nashorn.internal.codegen.types.Type.generic
import java.lang.invoke.LambdaMetafactory
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType


open class Function2Connector : AbstractConnector() {
    override fun validateInputParameters() {
    }

    override fun executeBusinessLogic() {
        val functionClassName: String = getInputParameter("functionClassName") as String
        val functionParam1: Any = getInputParameter("functionParam1")
        val functionParam2: Any = getInputParameter("functionParam2")
        val clazz = Class.forName(functionClassName)
        val method = clazz.declaredMethods.find { it.name == "invoke" }
//        val methodType = MethodType.methodType(Object::class.java, Object::class.java)
//        val lookup = MethodHandles.lookup()
//        val handle = lookup.findStatic(clazz, "transform", methodType)
//        val f = LambdaMetafactory.metafactory(
//                lookup, "apply",
//                MethodType.methodType(Function::class.java),
//                methodType.generic(),
//                handle,
//                methodType)
//                .target.invokeExact() as Function<String, Boolean>

        val result = method?.invoke(clazz.newInstance(), functionParam1, functionParam2)
        setOutputParameter("result", result)
    }


}
