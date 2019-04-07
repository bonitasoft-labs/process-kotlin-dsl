package com.bonitasoft.engine.dsl.process

import com.winterbe.expekt.should
import org.junit.jupiter.api.assertThrows
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

/**
 * @author Danila Mazour
 */
object ConditionTest : Spek({

    describe("Verify that conditions are correctly created") {
        it("should have generated the correct expression from simple condition") {
            val condition = Condition()
            condition.groovy("return true")
            condition.build(DataContainer()).apply {
                returnType.should.equal("java.lang.Boolean")
                content.should.equal("return true")
                interpreter.should.equal("GROOVY")
                dependencies.should.be.empty
            }
        }
        it("should generate groovy script expression with dependencies") {
            val dataContainer = DataContainer()
                    .data {
                        name = "myData"
                        type = DataType.string()
                    }
                    .data {
                        name = "myBiggestData"
                        type = DataType.string()
                    }
            val condition = Condition()
            condition.groovy("return true") {
                dataRef("myData")
                dataRef("myBiggestData")
            }
            condition.build(dataContainer).apply {
                content.should.equal("return true")
                dependencies.should.have.size(2)
                dependencies.map { it.name }.should.equal(listOf("myData", "myBiggestData"))
            }
        }
        it("should fail when generating groovy script expression with missing dependencies") {
            val condition = Condition()
            condition.groovy("return true") {
                dataRef("myData")
                dataRef("myBiggestData")
            }
            assertThrows<IllegalArgumentException> { condition.build(DataContainer()) }
        }
        it("should generate data dependency expression") {
            val dataContainer = DataContainer().data {
                name = "myData"
                type = DataType.string()
            }
            val condition = Condition()
            condition.dataRef("myData")
            condition.build(dataContainer).apply {
                content.should.equal("myData")
                expressionType.should.equal("TYPE_VARIABLE")
            }
        }
    }

})
