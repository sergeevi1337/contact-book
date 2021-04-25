package com.university.contactbook.controller;

import com.university.contactbook.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping(value = "/upload_file", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String process(@RequestParam("upload") MultipartFile file) throws Exception {
        String uploadedFileName = uploadService.upload(file);
        String uploadedFilePath = "/file/" + uploadedFileName;

        return "{" +
                "\"uploaded\":1," +
                "\"fileName\":\"" + file.getOriginalFilename() + "\"," +
                "\"url\":\"" + uploadedFilePath + "\"" +
                "}";
    }
}

