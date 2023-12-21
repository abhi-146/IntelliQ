package com.example.Intelliq.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompatibilityCheckService {

    public String checkCompatibility(String resumeText, String jobDescription) {
        Set<String> resumeKeywords = extractKeywords(resumeText);
        Set<String> jobDescKeywords = extractKeywords(jobDescription);

        // Find the intersection of both sets
        resumeKeywords.retainAll(jobDescKeywords);

        // Calculate a simple compatibility score (for demonstration)
        int score = resumeKeywords.size();
        String result = "Compatibility Score: " + score ;

        return result;
    }

    private Set<String> extractKeywords(String text) {
        // Split text into words and remove common English words (stop words)
        Set<String> stopWords = Set.of("and", "the", "of", "to", "a", "in", "for", "is", "on", "that", "by", "this", "with", "i", "you", "it", "not", "or", "be", "are", "from", "at", "as", "your", "all", "have", "new", "more", "an", "was", "we", "will", "home", "can", "us", "about", "if", "page", "my", "has", "search", "free", "but", "our", "one", "other", "do", "no", "information", "time", "they", "site", "he", "up", "may", "what", "which", "their", "news", "out", "use", "any", "there", "see", "only", "so", "his", "when", "contact", "here", "business", "who", "web", "also", "now", "help", "get", "pm", "view", "online", "first", "am", "been", "would", "how", "were", "me", "s");

        return Arrays.stream(text.toLowerCase().split("\\W+"))
                .filter(word -> !word.isEmpty() && !stopWords.contains(word))
                .collect(Collectors.toSet());
    }
}
