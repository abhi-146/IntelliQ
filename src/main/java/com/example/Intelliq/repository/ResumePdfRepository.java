package com.example.Intelliq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Intelliq.model.ResumePdf;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumePdfRepository extends JpaRepository<ResumePdf, Long> {

}

