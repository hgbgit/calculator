package com.loanpro.calculator.services.strategy;

import com.loanpro.calculator.client.RandomStringClient;
import com.loanpro.calculator.common.EOperation;
import com.loanpro.calculator.payload.request.CalculationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RandomStringOperation implements OperationStrategy<String> {

    private static final Integer AMOUNT_OF_STRINGS = 1;

    private static final Integer STRING_LENGTH = 8;

    private final RandomStringClient randomStringClient;
    private final String digits;
    private final String upperAlpha;
    private final String lowerAlpha;
    private final String unique;
    private final String rnd;
    private final String format;

    public RandomStringOperation(final RandomStringClient randomStringClient,
                                 @Value("${random.strings.digits}") final String digits,
                                 @Value("${random.strings.upperalpha}") final String upperAlpha,
                                 @Value("${random.strings.loweralpha}") final String lowerAlpha,
                                 @Value("${random.strings.unique}") final String unique,
                                 @Value("${random.strings.rnd}") final String rnd,
                                 @Value("${random.strings.format}") final String format) {

        this.randomStringClient = randomStringClient;
        this.digits = digits;
        this.upperAlpha = upperAlpha;
        this.lowerAlpha = lowerAlpha;
        this.unique = unique;
        this.rnd = rnd;
        this.format = format;
    }


    @Override
    public Boolean canHandle(CalculationRequest calculationRequest) {
        return EOperation.RANDOM_STRING.equals(calculationRequest.operation());
    }

    @Override
    public String handle(CalculationRequest calculationRequest) {
        return randomStringClient.getRandomString(AMOUNT_OF_STRINGS, STRING_LENGTH, digits, upperAlpha, lowerAlpha, unique, rnd, format);
    }
}
