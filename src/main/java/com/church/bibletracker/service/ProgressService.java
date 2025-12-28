package com.church.bibletracker.service;

import com.church.bibletracker.config.BibleConfig;
import com.church.bibletracker.model.BibleBook;
import com.church.bibletracker.model.CurrentReading;
import com.church.bibletracker.model.ReadingProgress;
import com.church.bibletracker.repository.ReadingProgressRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressService {

    private final BibleConfig bibleConfig;

    private final ReadingProgressRepository progressRepository;

    public ProgressService(BibleConfig bibleConfig, ReadingProgressRepository progressRepository) {

        this.bibleConfig = bibleConfig;
        this.progressRepository = progressRepository;
    }

    public void markAsRead(String name, String book, int chapter) {

        ReadingProgress progress = new ReadingProgress(name, book, chapter, LocalDateTime.now());
        progressRepository.save(progress);
    }

    public List<ReadingProgress> getProgressByName(String name) {

        return progressRepository.findByNameOrderByDateTimeReadAsc(name);
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
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            
            return reader.lines()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
