package com.loanpro.calculator.services.strategy;

import com.loanpro.calculator.client.RandomStringClient;
import com.loanpro.calculator.payload.request.CalculationRequest;
import com.loanpro.calculator.common.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RandomStringOperation implements OperationStrategy<String> {


    private final RandomStringClient randomStringClient;
    private final Integer num;
    private final Integer len;
    private final String digits;
    private final String upperAlpha;
    private final String lowerAlpha;
    private final String unique;
    private final String rnd;
    private final String format;

    @Autowired
    public RandomStringOperation(final RandomStringClient randomStringClient,
                                 @Value("${random.string.num}") final Integer num,
                                 @Value("${random.string.len}") final Integer len,
                                 @Value("${random.string.digits}") final String digits,
                                 @Value("${random.string.upperalpha}") final String upperAlpha,
                                 @Value("${random.string.loweralpha}") final String lowerAlpha,
                                 @Value("${random.string.unique}") final String unique,
                                 @Value("${random.string.rnd}") final String rnd,
                                 @Value("${random.string.format}") final String format) {

        this.randomStringClient = randomStringClient;
        this.num = num;
        this.len = len;
        this.digits = digits;
        this.upperAlpha = upperAlpha;
        this.lowerAlpha = lowerAlpha;
        this.unique = unique;
        this.rnd = rnd;
        this.format = format;
    }


    @Override
    public Boolean canHandle(CalculationRequest calculationRequest) {
        return Operation.RANDOM_STRING.equals(calculationRequest.operation());
    }

    @Override
    public String handle(CalculationRequest calculationRequest) {
        return randomStringClient.getRandomString(num, len, digits, upperAlpha, lowerAlpha, unique, rnd, format);
    }
}
