package com.example.Intelliq.controller;

import com.example.Intelliq.service.CompatibilityCheckService;
import com.example.Intelliq.service.PdfExtractionService;
import com.example.Intelliq.service.ResumePdfStorageService;
import com.example.Intelliq.service.WebDataExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResumePdfController {

    @Autowired
    private ResumePdfStorageService resumePdfStorageService;

    @Autowired
    private PdfExtractionService pdfExtractionService;

    @Autowired
    CompatibilityCheckService compatibilityCheckService;
    @Autowired
    private WebDataExtractionService webDataExtractionService;
    @CrossOrigin
    @PostMapping("/check")
    public String extractTextFromPdf(@RequestParam("file") MultipartFile file,
                                     @RequestParam("url") String jobUrl) {
        try {
            String webText = webDataExtractionService.extractDataFromWebPage(jobUrl);
            resumePdfStorageService.storeResumePdf(file);
            String extractedText = pdfExtractionService.extractTextFromPdf(file);
            resumePdfStorageService.storeResumePdf(file);
            // Call the compatibility check service

            String compatibility = compatibilityCheckService.checkCompatibility(extractedText, webText);

            return compatibility;
        }
         catch (Exception e) {
            return "Failed to extract text: " + e.getMessage();
        }
    }
}
