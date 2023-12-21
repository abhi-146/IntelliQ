package com.example.Intelliq.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WebDataExtractionService {

    public String extractDataFromWebPage(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String webPageContent = doc.text(); // Extracts text from the webpage
        System.out.println(webPageContent);
        return webPageContent;
    }
}
