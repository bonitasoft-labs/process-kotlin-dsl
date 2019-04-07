package com.bonitasoft.engine.dsl.process

import org.bonitasoft.engine.expression.Expression
import org.bonitasoft.engine.expression.ExpressionBuilder
import org.bonitasoft.engine.expression.ExpressionConstants


object Expressions {
    fun caseId(): Expression {
        return ExpressionBuilder().createEngineConstant(ExpressionConstants.PROCESS_INSTANCE_ID)
    }
}
