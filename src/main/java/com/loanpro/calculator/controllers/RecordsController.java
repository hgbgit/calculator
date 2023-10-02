package com.loanpro.calculator.controllers;

import com.loanpro.calculator.payload.response.RecordResponse;
import com.loanpro.calculator.services.RecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordsController {

    private final RecordsService recordsService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public Page<RecordResponse> listRecords(Pageable pageable) {
        return recordsService.listRecords(pageable);
    }

}
