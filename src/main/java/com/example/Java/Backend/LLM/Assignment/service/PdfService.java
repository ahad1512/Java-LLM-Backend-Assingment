package com.example.Java.Backend.LLM.Assignment.service;


import com.example.Java.Backend.LLM.Assignment.model.ExtractedData;
import com.example.Java.Backend.LLM.Assignment.utils.LlmProcessor;
import com.example.Java.Backend.LLM.Assignment.utils.PasswordGenerator;
import com.example.Java.Backend.LLM.Assignment.utils.PdfParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PdfService {

    @Autowired
    private LlmProcessor llmProcessor;

    public ExtractedData processPdf(File pdfFile, String firstName, String dob) throws IOException {
        String extractedText = PdfParser.extractText(pdfFile);
        ExtractedData data = llmProcessor.processTextWithLLM(extractedText);

        // Generate Password
        String generatedPassword = PasswordGenerator.generatePassword(firstName, dob);
        data.setPassword(generatedPassword);

        return data;
    }
}
