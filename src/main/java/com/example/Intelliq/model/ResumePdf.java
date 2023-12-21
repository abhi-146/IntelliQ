package com.example.Intelliq.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resume_pdfs")
public class ResumePdf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Lob
    @Column(name = "data")
    private byte[] data;

    // Constructors, getters, and setters

    public ResumePdf() {}

    public ResumePdf(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
