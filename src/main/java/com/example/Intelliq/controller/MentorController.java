package com.example.Intelliq.controller;

import com.example.Intelliq.model.Mentor;
import com.example.Intelliq.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentor")
@CrossOrigin
public class MentorController {
    @Autowired
    private MentorService mentorService;

    @PostMapping("/add")
    public String add(@RequestBody Mentor mentor){
        mentorService.saveMentor(mentor);
        return "New mentor is added";
    }

    @GetMapping("/getAll")
    public List<Mentor> list(){
        return mentorService.getAllMentors();
    }
}
