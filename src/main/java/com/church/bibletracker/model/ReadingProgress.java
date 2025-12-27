package com.church.bibletracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadingProgress {

    private String name;

    private String book;

    private int chapter;

    private LocalDateTime dateTimeRead;

}
