package com.spring.pi.repositories;

import com.spring.pi.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findContractByValid(Boolean Valid);
    @Query("SELECT c.price_Cont FROM Contract c "
            + "GROUP BY c.price_Cont "
            + "ORDER BY COUNT(c.price_Cont) DESC")
    List<String> MostContractPrice();
}