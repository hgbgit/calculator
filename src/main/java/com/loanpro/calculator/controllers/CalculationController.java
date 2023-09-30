package com.loanpro.calculator.controllers;

import com.loanpro.calculator.payload.request.CalculationRequest;
import com.loanpro.calculator.payload.response.CalculationResponse;
import com.loanpro.calculator.services.OperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/calculation")
@RequiredArgsConstructor
public class CalculationController {

    private final OperationService operationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public CalculationResponse authenticateUser(@Valid @RequestBody CalculationRequest calculationRequest) {
        String result = operationService.calculate(calculationRequest);
        return new CalculationResponse(result);
    }

}
