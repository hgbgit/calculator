package com.loanpro.calculator.controllers;

import com.loanpro.calculator.payload.response.RecordResponse;
import com.loanpro.calculator.services.RecordsService;
import jakarta.websocket.server.PathParam;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public Page<RecordResponse> listRecords(Pageable pageable, @RequestParam(required = false) String operation) {
        return recordsService.listRecords(pageable, operation);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/{id}")
    public void deleteRecord(@PathVariable("id") Long id) {
        recordsService.deleteRecord(id);
    }

}
