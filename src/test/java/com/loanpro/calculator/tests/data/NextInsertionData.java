package com.loanpro.calculator.tests.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class NextInsertionData {

    private UUID nextId;
    private UUID claimId;
    private LocalDateTime nextCreatedAt;
    private LocalDateTime nextUpdatedAt;
}
