package com.example.Intelliq.service;

import com.example.Intelliq.model.Mentor;
import com.example.Intelliq.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImpl implements MentorService { // Rename the class

    @Autowired
    private MentorRepository mentorRepository; // Update the repository type

    @Override
    public Mentor saveMentor(Mentor mentor) { // Update method signature and return type
        return mentorRepository.save(mentor);
    }

    @Override
    public List<Mentor> getAllMentors() { // Update method signature and return type
        return mentorRepository.findAll();
    }
}
