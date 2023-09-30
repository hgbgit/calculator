package com.loanpro.calculator.services.strategy;

import com.loanpro.calculator.payload.request.CalculationRequest;
import com.loanpro.calculator.common.EOperation;

import java.math.BigDecimal;

public class SubtractionOperation implements OperationStrategy<BigDecimal> {
    @Override
    public Boolean canHandle(CalculationRequest calculationRequest) {
        return EOperation.SUBTRACTION.equals(calculationRequest.operation());
    }

    @Override
    public BigDecimal handle(CalculationRequest calculationRequest) {
        return calculationRequest.a().subtract(calculationRequest.b());
    }
}
