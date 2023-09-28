package com.loanpro.calculator.services.strategy;

import com.loanpro.calculator.payload.request.CalculationRequest;
import com.loanpro.calculator.common.Operation;

import java.math.BigDecimal;
import java.math.MathContext;

public class SquareRootOperation implements OperationStrategy<BigDecimal> {
    @Override
    public Boolean canHandle(CalculationRequest calculationRequest) {
        return Operation.SQUARE_ROOT.equals(calculationRequest.operation());
    }

    @Override
    public BigDecimal handle(CalculationRequest calculationRequest) {
        return calculationRequest.valueA().sqrt(new MathContext(calculationRequest.valueB().intValue()));
    }
}
