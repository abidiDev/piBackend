package com.spring.pi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ContractValidationRequest {
    private long idContract;
    private LocalDate startDate;
    private LocalDate endDate;
}
