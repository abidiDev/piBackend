package com.spring.pi.services;

import com.spring.pi.entities.Contract;
import com.spring.pi.repositories.ContractRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


import java.io.FileNotFoundException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReportService {
   private ContractRepository contractRepository;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\4SAE8\\PIDEV";
        List<Contract> contracts = contractRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:Contracts.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(contracts);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Tsamsira Tunisie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\contracts.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\contracts.pdf");
        }

        return "report generated in path : " + path;
    }
}
