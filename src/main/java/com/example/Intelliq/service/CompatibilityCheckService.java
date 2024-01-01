package com.example.Intelliq.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Service
public class CompatibilityCheckService {

    public String checkCompatibility(String resumeText, String jobDescription) {
        List<String> resumeKeywords = advancedTextProcessing(resumeText);
        List<String> jobDescKeywords = advancedTextProcessing(jobDescription);

        Map<String, Double> keywordImportanceMap = calculateKeywordImportance(jobDescKeywords);
        double matchScore = calculateMatchScore(resumeKeywords, jobDescKeywords, keywordImportanceMap);

        double compatibilityIndex = applyStatisticalModel(matchScore, resumeKeywords.size(), jobDescKeywords.size());

        String result = String.format("Compatibility Index: %.2f%%", compatibilityIndex * 100);
        return result;
    }


    private List<String> advancedTextProcessing(String text) {

        return Stream.of(text.toLowerCase().split("\\W+"))
                .filter(word -> !word.isEmpty() && !isStopWord(word))
                .collect(Collectors.toList());
    }


    private Map<String, Double> calculateKeywordImportance(List<String> keywords) {
        Map<String, Double> importanceMap = new HashMap<>();

        List<String> programmingLanguages = Arrays.asList(
                "java", "python", "c++", "javascript", "ruby", "kotlin", "scala", "swift",
                "typescript", "php", "c#", "go", "perl", "rust", "r", "matlab", "sql", "groovy", "lua"
        );

        List<String> tools = Arrays.asList(
                "maven", "gradle", "docker", "kubernetes", "jenkins", "git", "github", "gitlab", "svn",
                "ansible", "puppet", "chef", "terraform", "vscode", "intellij", "eclipse", "netbeans",
                "jira", "trello", "slack", "aws", "azure", "gcp", "nginx", "apache", "tomcat"
        );

        // Assigning points based on the category of the keyword
        for(String keyword : keywords){
            if (programmingLanguages.contains(keyword.toLowerCase())) {
                importanceMap.put(keyword, 5.0); // 5 points for programming language
            } else if (tools.contains(keyword.toLowerCase())) {
                importanceMap.put(keyword, 3.0); // 3 points for tools
            } else {
                importanceMap.put(keyword, 1.0); // 1 point for any other word
            }
        }
        return importanceMap;
    }

    // Calculating match score
    private double calculateMatchScore(List<String> resumeKeywords, List<String> jobDescKeywords, Map<String, Double> importanceMap) {
        AtomicInteger matchCount = new AtomicInteger();
        resumeKeywords.forEach(word -> {
            if (jobDescKeywords.contains(word)) {
                matchCount.addAndGet((int) (1 * importanceMap.getOrDefault(word, 1.0)));
            }
        });

        return matchCount.get() / (double) jobDescKeywords.size(); // Normalized match score
    }

    private double applyStatisticalModel(double matchScore, int resumeLength, int jobDescLength) {

        double complexityFactor = 0.01 * (jobDescLength - resumeLength);
        return Math.tanh(matchScore - complexityFactor);
    }

    // Updated method to check for stop words
    private boolean isStopWord(String word) {
        Set<String> stopWords = Set.of("and", "the", "of", "to", "a", "in", "for", "is", "on", "that", "by", "this", "with", "i", "you", "it", "not", "or", "be", "are", "from", "at", "as", "your", "all", "have", "new", "more", "an", "was", "we", "will", "home", "can", "us", "about", "if", "page", "my", "has", "search", "free", "but", "our", "one", "other", "do", "no", "information", "time", "they", "site", "he", "up", "may", "what", "which", "their", "news", "out", "use", "any", "there", "see", "only", "so", "his", "when", "contact", "here", "business", "who", "web", "also", "now", "help", "get", "pm", "view", "online", "first", "am", "been", "would", "how", "were", "me", "s");
        return stopWords.contains(word);
    }
}
