package com.university.contactbook.service.impl;

import com.university.contactbook.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String upload(MultipartFile uploadedFile) throws IOException {
        log.debug("Uploading file");
        File rootUploadDir = new File(uploadPath);

        if (!rootUploadDir.exists()) {
            rootUploadDir.mkdir();
        }

        String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        File currentDayUploadDir = new File(uploadPath + "/" + currentDate);

        if (!currentDayUploadDir.exists()) {
            currentDayUploadDir.mkdir();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "_" + uploadedFile.getOriginalFilename();

        File file = new File(currentDayUploadDir + "/" + resultFilename);
        uploadedFile.transferTo(file);

        log.debug("Uploading finished!");
        return currentDate + "/" + file.getName();
    }
}
