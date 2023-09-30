package com.loanpro.calculator.services.strategy;

import com.loanpro.calculator.payload.request.CalculationRequest;
import com.loanpro.calculator.common.EOperation;

import java.math.BigDecimal;

public class DivisionOperation implements OperationStrategy<BigDecimal> {
    @Override
    public Boolean canHandle(CalculationRequest calculationRequest) {
        return EOperation.DIVISION.equals(calculationRequest.operation());
    }

    @Override
    public BigDecimal handle(CalculationRequest calculationRequest) {
        return calculationRequest.a().divide(calculationRequest.b());
    }
}
