package com.spring.pi.controllers;

import com.spring.pi.entities.Contract;
import com.spring.pi.payload.request.ContractRequest;
import com.spring.pi.payload.request.ContractValidationRequest;
import com.spring.pi.repositories.ContractRepository;
import com.spring.pi.services.ReportService;
import com.spring.pi.services.SellBuyModuleService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/Contract")
public class SellerBuyerController {
    SellBuyModuleService sellBuyModuleService;
    ContractRepository contractRepository;
    private ReportService service;



    @PostMapping("/addC")
    public Contract addAndAssignContractToRealEstateAndActorContract(@RequestBody ContractRequest contractRequest) {
       return sellBuyModuleService.addAndAssignContractToRealEstateAndActorContract(contractRequest);
    }
    @PostMapping("/validerC")
    public Contract validerContract(@RequestBody ContractValidationRequest cvr) {
        return sellBuyModuleService.validerContract(cvr);
    }
    @GetMapping("/test")
    public List<Contract> testcontract(){
        return contractRepository.findContractByValid(true);
    }
    @GetMapping("/stat")
    public List<String> Statistique() {
        return sellBuyModuleService.Statistique();
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);
    }
}


























