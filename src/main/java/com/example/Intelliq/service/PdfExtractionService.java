package com.example.Intelliq.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class PdfExtractionService {

    public String extractTextFromPdf(MultipartFile file) throws IOException {
        String extractedText;

        try (InputStream in = file.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(in));

             PDDocument document = Loader.loadPDF(file.getBytes())) {
            PDFTextStripper stripper = new PDFTextStripper();
            extractedText = stripper.getText(document);
        }
        System.out.println(extractedText);
        return extractedText;
    }
}

