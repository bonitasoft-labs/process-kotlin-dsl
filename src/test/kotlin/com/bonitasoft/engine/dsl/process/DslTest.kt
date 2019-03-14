/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.bonitasoft.engine.dsl.process

import com.winterbe.expekt.should
import org.bonitasoft.engine.bpm.flownode.AutomaticTaskDefinition
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object DslTest : Spek({

    describe("A process DSL containing tasks") {

        val process = process("MyProcess", "1.0") {
            automaticTask("Step1") {
            }
            automaticTask("Step2") {
            }
            automaticTask("Step3") {
            }
            transitions{
                from("Step1").to("Step2")
                from("Step2").to("Step3")
            }

        }
        val processDefinition = process.export()

        it("should have the right name and version") {
            processDefinition.name.should.equal("MyProcess")
            processDefinition.version.should.equal("1.0")
        }

        it("should have 2 automatic task") {
            processDefinition.flowElementContainer.activities.forEach {
                it.should.satisfy { e -> e is AutomaticTaskDefinition }
            }
        }
        it("should have 2 steps with the right name") {
            processDefinition.flowElementContainer.activities.should.have.size(3)
        }
        it("should have transition between step1 and step2") {
            val step1 = processDefinition.flowElementContainer.getActivity("Step1")
            step1.outgoingTransitions.should.have.size(1)
            step1.outgoingTransitions[0].targetFlowNode.name.should.equal("Step2")
        }
    }

    describe("A process DSL with parallel gateway") {

        val process = process("MyProcess", "1.0") {
            parallelGateway("gate1") {
            }
            automaticTask("Step1") {}
            automaticTask("Step2") {}
            automaticTask("Step3") {}

            transitions {
                from("gate1"){
                    to("Step1")
                    to("Step2")
                    to("Step3")
                }
            }
        }
        val processDefinition = process.export()

        it("should have the parallel gateway with step1, step2 and step3") {
            processDefinition.flowElementContainer.getFlowNode("gate1").outgoingTransitions.should.have.size.equal(3)
        }
    }
    describe("A process DSL with exclusive gateway") {

        val process = process("MyProcess", "1.0") {
            exclusiveGateway("gate1") {}
            transitions {
                from("gate1") {
                    to("Step1").isDefault()
                    to("Step2")
                    to("Step3")
                }
            }
            automaticTask("Step1") {}
            automaticTask("Step2") {}
            automaticTask("Step3") {}
        }
        val processDefinition = process.export()

        it("should have the parallel gateway with step1, step2 and step3") {
            processDefinition.flowElementContainer.getFlowNode("gate1").outgoingTransitions.should.have.size.equal(3)
        }
    }
})
