package com.loanpro.calculator.payload.request;

import com.loanpro.calculator.common.Operation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
public record CalculationRequest(@NotBlank BigDecimal valueA, @NotBlank BigDecimal valueB,
                                 @NotBlank Operation operation) {
}
