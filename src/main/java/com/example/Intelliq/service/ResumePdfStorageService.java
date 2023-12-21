package com.example.Intelliq.service;

import com.example.Intelliq.model.ResumePdf;
import com.example.Intelliq.repository.ResumePdfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumePdfStorageService {
    @Autowired
    private ResumePdfRepository resumePdfRepository;

    public void storeResumePdf(MultipartFile file) throws IOException {
        System.out.println(file.getBytes().length);
        ResumePdf resumePdf = new ResumePdf(file.getOriginalFilename(), file.getBytes());
        resumePdfRepository.save(resumePdf);
    }
}
