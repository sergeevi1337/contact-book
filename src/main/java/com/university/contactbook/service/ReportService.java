package com.university.contactbook.service;

import com.university.contactbook.utils.enums.ReportFormat;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.FileNotFoundException;

public interface ReportService {

    File exportReport(ReportFormat format) throws FileNotFoundException, JRException;
}
