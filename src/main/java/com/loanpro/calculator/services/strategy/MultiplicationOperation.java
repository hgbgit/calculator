package com.loanpro.calculator.services.strategy;

import com.loanpro.calculator.payload.request.CalculationRequest;
import com.loanpro.calculator.common.EOperation;

import java.math.BigDecimal;

public class MultiplicationOperation implements OperationStrategy<BigDecimal> {
    @Override
    public Boolean canHandle(CalculationRequest calculationRequest) {
        return EOperation.MULTIPLICATION.equals(calculationRequest.operation());
    }

    @Override
    public BigDecimal handle(CalculationRequest calculationRequest) {
        return calculationRequest.a().multiply(calculationRequest.b());
    }
}
