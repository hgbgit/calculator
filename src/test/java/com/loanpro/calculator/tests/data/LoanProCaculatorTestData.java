package com.loanpro.calculator.tests.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.test.web.servlet.ResultActions;

@Getter
@Setter
@ToString
public class LoanProCaculatorTestData {

    private ResultActions resultActions;
    private String requestJson;
    private NextInsertionData nextInsertionData;


    public LoanProCaculatorTestData() {
        this.reset();
    }

    public void reset() {
        this.resultActions = null;
        this.requestJson = null;
        this.nextInsertionData = null;
    }

}
