package com.university.contactbook.service.impl;

import com.university.contactbook.entity.Contact;
import com.university.contactbook.repository.ContactRepository;
import com.university.contactbook.service.ReportService;
import com.university.contactbook.utils.enums.ReportFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    @Value("${reports.path}")
    private String reportsPath;

    private final ContactRepository contactRepository;

    @Override
    public File exportReport(ReportFormat format) throws FileNotFoundException, JRException {
        String destinationFilePath;
        File rootUploadDir = new File(reportsPath);

        if (!rootUploadDir.exists()) {
            rootUploadDir.mkdir();
        }

        List<Contact> contacts = contactRepository.findAllByDeletedFalse();

        File file = ResourceUtils.getFile("classpath:reports/contacts.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(contacts);

        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (format == ReportFormat.PDF) {
            destinationFilePath = reportsPath + "/contacts.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, destinationFilePath);
        } else if (format == ReportFormat.HTML) {
            destinationFilePath = reportsPath + "/contacts.html";
            JasperExportManager.exportReportToHtmlFile(jasperPrint, destinationFilePath);
        } else {
            throw new RuntimeException("Unsupported report type");
        }

        return new File(destinationFilePath);
    }
}
