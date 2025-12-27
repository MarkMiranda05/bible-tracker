package com.church.bibletracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reading_progress")
public class ReadingProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String book;

    @Column(nullable = false)
    private int chapter;

    @Column(name = "datetime_read", nullable = false)
    private LocalDateTime dateTimeRead;

    public ReadingProgress(String name, String book, int chapter, LocalDateTime dateTimeRead) {

        this.name = name;
        this.book = book;
        this.chapter = chapter;
        this.dateTimeRead = dateTimeRead;
    }

}
