/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.bonitasoft.engine.dsl.process

import com.bonitasoft.engine.dsl.process.DataType.Companion.boolean
import com.bonitasoft.engine.dsl.process.DataType.Companion.string
import com.bonitasoft.engine.dsl.process.ExpressionDSLBuilder.ExpressionDSLBuilderObject.contract
import com.bonitasoft.engine.dsl.process.ExpressionDSLBuilder.ExpressionDSLBuilderObject.groovy
import com.winterbe.expekt.should
import org.bonitasoft.engine.bpm.contract.Type
import org.bonitasoft.engine.bpm.flownode.UserTaskDefinition
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object ContractTest : Spek({

    describe("A process with contracts") {

        it("should have the right name and version") {
            val process = process("MyProcess", "1.0") {
                initiator("walter")
                val john = actor("john")
                data {
                    name = "myData"
                    type = boolean()
                }
                contract {
                    text named "type" withDescription "The type of the loan"
                    long named "amount" withDescription "The amount of the loan"
                }
                userTask("myTask") {
                    actor = john
                    contract {
                        boolean named "accept" withDescription "whether to accept the loan or not"
                    }
                    operations {
                        update("myData").with(contract("accept"))
                    }
                }

            }
            val processDefinition = process.export().processDefinition

            val task = processDefinition.flowElementContainer.getActivity("myTask") as UserTaskDefinition
            task.actorName.should.equal("john")
            task.contract.inputs.should.have.size(1)
            task.contract.inputs[0].type.should.equal(Type.BOOLEAN)
            task.contract.inputs[0].name.should.equal("accept")
            task.contract.inputs[0].description.should.equal("whether to accept the loan or not")

            processDefinition.contract.inputs.should.have.size(2)
            processDefinition.contract.inputs.map { it.name }.should.equal(listOf("type", "amount"))
        }
    }
})
