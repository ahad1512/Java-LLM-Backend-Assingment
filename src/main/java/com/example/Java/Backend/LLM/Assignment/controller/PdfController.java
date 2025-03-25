package com.example.Java.Backend.LLM.Assignment.controller;

import com.example.Java.Backend.LLM.Assignment.model.ExtractedData;
import com.example.Java.Backend.LLM.Assignment.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/upload")
    public ResponseEntity<ExtractedData> uploadPdf(
            @RequestParam("file") MultipartFile file,
            @RequestParam("firstName") String firstName,
            @RequestParam("dob") String dob) {

        try {
            File pdfFile = File.createTempFile("uploaded", ".pdf");
            file.transferTo(pdfFile);
            ExtractedData extractedData = pdfService.processPdf(pdfFile, firstName, dob);
            return ResponseEntity.ok(extractedData);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
