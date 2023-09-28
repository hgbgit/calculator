package com.loanpro.calculator.services;

import com.loanpro.calculator.models.User;
import com.loanpro.calculator.payload.request.CalculationRequest;
import com.loanpro.calculator.security.services.UserDetailsServiceImpl;
import com.loanpro.calculator.services.strategy.OperationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final List<OperationStrategy> operationStrategies;

    private final UserDetailsServiceImpl userDetailsService;

    public String calculate(CalculationRequest calculationRequest) {
        User user = userDetailsService.getCurrentUser();
        return null;
    }

}
