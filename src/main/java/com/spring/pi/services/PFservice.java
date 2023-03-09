package com.spring.pi.services;

import com.spring.pi.entities.Actor_construction;
import com.spring.pi.repositories.ActorConstructionRepostory;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.jasperreports.engine.JasperCompileManager.*;

@Service
@AllArgsConstructor
public class PFservice {
    ActorConstructionRepostory actorConstructionRepostory;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\USER\\Desktop\\pdf";
        List<Actor_construction> contracts = actorConstructionRepostory.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:construction.jrxml");
        JasperReport jasperReport = compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(contracts);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Tsamsira Tunisie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\construction.pdf");


        return "report generated in path : " + path;
    }

}
