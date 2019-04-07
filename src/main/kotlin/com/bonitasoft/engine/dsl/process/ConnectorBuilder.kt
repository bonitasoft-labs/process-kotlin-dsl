package com.bonitasoft.engine.dsl.process

import com.bonitasoft.engine.dsl.process.connectors.Function2Connector
import org.bonitasoft.engine.bpm.bar.BarResource
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder
import org.bonitasoft.engine.bpm.connector.ConnectorEvent
import org.bonitasoft.engine.bpm.process.impl.FlowElementContainerBuilder
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream

class ConnectorBuilder {

    private var outputOperations: ConnectorOutputsContainer? = null
    private var inputs: ConnectorInputsContainer? = null
    var className: String? = null

    private var inputsForFunction: List<ExpressionDSLBuilder>? = null
    private var function0: Any? = null
    private var function1: Any? = null
    private var function2: Any? = null

    private var connectorEvent = ConnectorEvent.ON_ENTER

    fun execute() {

    }

    fun inputs(init: ConnectorInputsContainer.() -> Unit) {
        inputs = ConnectorInputsContainer().apply(init)
    }

    fun inputs(vararg inputs: ExpressionDSLBuilder) {
        inputsForFunction = inputs.toList()
    }

    fun outputs(init: ConnectorOutputsContainer.() -> Unit) {
        outputOperations = ConnectorOutputsContainer().apply(init)
    }

    fun <O> execute(function: Function0<O>) {
        this.function0 = function
    }

    fun <I1, O> execute(function: Function1<I1, O>) {
        this.function1 = function
    }

    fun <I1, I2, O> execute(function: Function2<I1, I2, O>) {
        this.function2 = function
    }

    fun build(builder: FlowElementContainerBuilder, businessArchiveBuilder: BusinessArchiveBuilder, dataContainer: DataContainer) {
        val connectorId = UUID.randomUUID().toString()
        if (className != null) {

        } else if (function2 != null) {
            val classToCall = function2!!::class.java
            var jar = getJar(classToCall, Function2Connector::class.java)
            businessArchiveBuilder.addClasspathResource(BarResource("connector-${connectorId}.jar", jar))
            val buildConnectorImplementationFile = buildConnectorImplementationFile(connectorId, "1.0",
                    connectorId, "1.0",
                    Function2Connector::class.java.name, listOf("connector-${connectorId}.jar"))
            businessArchiveBuilder.addConnectorImplementation(BarResource("connector-${connectorId}.impl", buildConnectorImplementationFile))
            val connectorDefinitionBuilder = builder.addConnector(connectorId, connectorId, "1.0", connectorEvent)
                    .addInput("functionClassName", classToCall.name.toExpression())
            inputsForFunction?.forEachIndexed { index, expr ->
                connectorDefinitionBuilder.addInput("functionParam" + (index + 1), expr.build(dataContainer))
            }
        }
    }

    fun getJar(vararg classes: Class<out Any>): ByteArray {
        val map = classes.associate { c ->
            val className = c.name.replace(".", "/") + ".class"
            className to c.classLoader.getResourceAsStream(className).readBytes()
        }
        var baos = ByteArrayOutputStream()
        var jarOutStream = JarOutputStream(BufferedOutputStream(baos))
        map.forEach { name, content ->
            jarOutStream.putNextEntry(JarEntry(name))
            jarOutStream.write(content)
        }
        jarOutStream.flush()
        baos.flush()
        return baos.toByteArray()
    }

    fun buildConnectorImplementationFile(definitionId: String, definitionVersion: String, implementationId: String,
                                         implementationVersion: String, implementationClassname: String, dependencies: List<String>): ByteArray {
        val jarDependencies = dependencies.map { "\n        <jarDependency>$it</jarDependency>" }
        val content = """
<?xml version="1.0" encoding="UTF-8"?>
<implementation:connectorImplementation xmlns:implementation="http://www.bonitasoft.org/ns/connector/implementation/6.0">
    <definitionId>$definitionId</definitionId>
    <definitionVersion>$definitionVersion</definitionVersion>
    <implementationClassname>$implementationId</implementationClassname>
    <implementationId>$implementationVersion</implementationId>
    <implementationVersion>$implementationClassname</implementationVersion>
    <jarDependencies>${jarDependencies}
    </jarDependencies>
</implementation:connectorImplementation>"""
        return content.toByteArray()
    }

}
