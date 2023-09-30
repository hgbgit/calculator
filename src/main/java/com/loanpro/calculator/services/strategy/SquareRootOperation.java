package com.loanpro.calculator.services.strategy;

import com.loanpro.calculator.payload.request.CalculationRequest;
import com.loanpro.calculator.common.EOperation;

import java.math.BigDecimal;
import java.math.MathContext;

public class SquareRootOperation implements OperationStrategy<BigDecimal> {
    @Override
    public Boolean canHandle(CalculationRequest calculationRequest) {
        return EOperation.SQUARE_ROOT.equals(calculationRequest.operation());
    }

    @Override
    public BigDecimal handle(CalculationRequest calculationRequest) {
        return calculationRequest.a().sqrt(new MathContext(calculationRequest.b().intValue()));
    }
}
