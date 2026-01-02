package com.church.bibletracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadingProgressDTO {

    private Long id;

    private String name;

    private String book;

    private int chapter;

    private String dateTimeReadManila;

    public ReadingProgressDTO(ReadingProgress progress) {

        this.id = progress.getId();
        this.name = progress.getName();
        this.book = progress.getBook();
        this.chapter = progress.getChapter();

        ZonedDateTime manilaTime =
                progress.getDateTimeRead().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("Asia/Manila"));

        this.dateTimeReadManila = manilaTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
