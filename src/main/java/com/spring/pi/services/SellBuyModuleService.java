package com.spring.pi.services;

import com.spring.pi.entities.Contract;
import com.spring.pi.payload.request.ContractRequest;
import com.spring.pi.payload.request.ContractValidationRequest;

import java.util.List;

public interface SellBuyModuleService {
    public Contract addAndAssignContractToRealEstateAndActorContract(ContractRequest contractRequest);
    public Contract validerContract(ContractValidationRequest cvr);
    public List<String> Statistique();
    public void NotifySellerandBuyer();

}
