package com.example.Intelliq.service;

import com.example.Intelliq.model.Mentor;

import java.util.List;

public interface MentorService {
    public Mentor saveMentor(Mentor mentor);
    public List<Mentor> getAllMentors();
}
