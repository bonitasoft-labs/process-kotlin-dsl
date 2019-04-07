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
            val condition = Condition(DataContainer())
            condition.groovy("return true")
            condition.build().apply {
                returnType.should.equal("java.lang.Boolean")
                content.should.equal("return true")
                interpreter.should.equal("GROOVY")
                dependencies.should.be.empty
            }
        }
        it("should generate groovy script expression with dependencies") {
            val condition = Condition(DataContainer()
                    .data {
                        name = "myData"
                        type = DataType.string()
                    }
                    .data {
                        name = "myBiggestData"
                        type = DataType.string()
                    }
            )
            condition.groovy("return true") {
                dataRef("myData")
                dataRef("myBiggestData")
            }
            condition.build().apply {
                content.should.equal("return true")
                dependencies.should.have.size(2)
                dependencies.map { it.name }.should.equal(listOf("myData", "myBiggestData"))
            }
        }
        it("should fail when generating groovy script expression with missing dependencies") {
            val condition = Condition(DataContainer())
            condition.groovy("return true") {
                dataRef("myData")
                dataRef("myBiggestData")
            }
            assertThrows<IllegalArgumentException> { condition.build() }
        }
        it("should generate data dependency expression") {
            val condition = Condition(DataContainer().data {
                name = "myData"
                type = DataType.string()
            })
            condition.dataRef("myData")
            condition.build().apply {
                content.should.equal("myData")
                expressionType.should.equal("TYPE_VARIABLE")
            }
        }
    }

})
