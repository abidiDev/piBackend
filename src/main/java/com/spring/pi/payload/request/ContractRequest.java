package com.spring.pi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ContractRequest {
    private long idProvider;
    private long idConsumer;
    private long idRealEstate;
    private LocalDate begin_Date;
     private LocalDate end_Date;
    private LocalDate createdDate;
    private float price_Cont;
    private Date DateTrans;
}
