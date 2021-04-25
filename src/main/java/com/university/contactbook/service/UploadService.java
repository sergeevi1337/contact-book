package com.university.contactbook.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    String upload(MultipartFile uploadedFile) throws IOException;
}
