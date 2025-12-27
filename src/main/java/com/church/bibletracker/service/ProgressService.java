package com.church.bibletracker.service;

import com.church.bibletracker.config.BibleConfig;
import com.church.bibletracker.model.BibleBook;
import com.church.bibletracker.model.CurrentReading;
import com.church.bibletracker.model.ReadingProgress;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressService {

    private static final String CSV_FILE = "reading_progress.csv";

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final BibleConfig bibleConfig;

    public ProgressService(BibleConfig bibleConfig) {

        this.bibleConfig = bibleConfig;
        initializeCsvFile();
    }

    private void initializeCsvFile() {

        File file = new File(CSV_FILE);
        if (!file.exists()) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
                writer.writeNext(new String[]{"name", "book", "chapter", "datetime_read"});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void markAsRead(String name, String book, int chapter) {

        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE, true))) {
            writer.writeNext(
                    new String[]{name, book, String.valueOf(chapter), LocalDateTime.now().format(DATETIME_FORMAT)});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ReadingProgress> getProgressByName(String name) {

        List<ReadingProgress> progressList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            String[] line;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(name)) {
                    progressList.add(new ReadingProgress(line[0], line[1], Integer.parseInt(line[2]),
                                                         LocalDateTime.parse(line[3], DATETIME_FORMAT)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return progressList;
    }

    public CurrentReading getCurrentReading(String name) {

        List<ReadingProgress> progress = getProgressByName(name);
        List<BibleBook> books = bibleConfig.getBooks();

        if (progress.isEmpty()) {
            return new CurrentReading("Genesis", 1);
        }

        // Find the last read chapter
        ReadingProgress lastRead = progress.get(progress.size() - 1);
        String lastBook = lastRead.getBook();
        int lastChapter = lastRead.getChapter();

        // Find next chapter
        for (int i = 0; i < books.size(); i++) {
            BibleBook book = books.get(i);
            if (book.name().equals(lastBook)) {
                if (lastChapter < book.chapters()) {
                    return new CurrentReading(lastBook, lastChapter + 1);
                } else {
                    if (i + 1 < books.size()) {
                        return new CurrentReading(books.get(i + 1).name(), 1);
                    } else {
                        // Completed the Bible!
                        return new CurrentReading("Completed", 0);
                    }
                }
            }
        }

        return new CurrentReading("Genesis", 1);
    }

    public List<String> getAllUsers() {

        try {
            ClassPathResource resource = new ClassPathResource("users.txt");
            return Files.readAllLines(resource.getFile().toPath())
                        .stream()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
