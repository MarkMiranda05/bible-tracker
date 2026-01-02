package com.church.bibletracker.config;

import com.church.bibletracker.model.BibleBook;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class BibleConfig {

    private final List<BibleBook> books;

    public BibleConfig() {

        books = new ArrayList<>();
        // Old Testament
        books.add(new BibleBook("Genesis", 50));
        books.add(new BibleBook("Exodus", 40));
        books.add(new BibleBook("Leviticus", 27));
        books.add(new BibleBook("Numbers", 36));
        books.add(new BibleBook("Deuteronomy", 34));
        books.add(new BibleBook("Joshua", 24));
        books.add(new BibleBook("Judges", 21));
        books.add(new BibleBook("Ruth", 4));
        books.add(new BibleBook("1 Samuel", 31));
        books.add(new BibleBook("2 Samuel", 24));
        books.add(new BibleBook("1 Kings", 22));
        books.add(new BibleBook("2 Kings", 25));
        books.add(new BibleBook("1 Chronicles", 29));
        books.add(new BibleBook("2 Chronicles", 36));
        books.add(new BibleBook("Ezra", 10));
        books.add(new BibleBook("Nehemiah", 13));
        books.add(new BibleBook("Esther", 10));
        books.add(new BibleBook("Job", 42));
        books.add(new BibleBook("Psalms", 150));
        books.add(new BibleBook("Proverbs", 31));
        books.add(new BibleBook("Ecclesiastes", 12));
        books.add(new BibleBook("Song of Solomon", 8));
        books.add(new BibleBook("Isaiah", 66));
        books.add(new BibleBook("Jeremiah", 52));
        books.add(new BibleBook("Lamentations", 5));
        books.add(new BibleBook("Ezekiel", 48));
        books.add(new BibleBook("Daniel", 12));
        books.add(new BibleBook("Hosea", 14));
        books.add(new BibleBook("Joel", 3));
        books.add(new BibleBook("Amos", 9));
        books.add(new BibleBook("Obadiah", 1));
        books.add(new BibleBook("Jonah", 4));
        books.add(new BibleBook("Micah", 7));
        books.add(new BibleBook("Nahum", 3));
        books.add(new BibleBook("Habakkuk", 3));
        books.add(new BibleBook("Zephaniah", 3));
        books.add(new BibleBook("Haggai", 2));
        books.add(new BibleBook("Zechariah", 14));
        books.add(new BibleBook("Malachi", 4));
        // New Testament
        books.add(new BibleBook("Matthew", 28));
        books.add(new BibleBook("Mark", 16));
        books.add(new BibleBook("Luke", 24));
        books.add(new BibleBook("John", 21));
        books.add(new BibleBook("Acts", 28));
        books.add(new BibleBook("Romans", 16));
        books.add(new BibleBook("1 Corinthians", 16));
        books.add(new BibleBook("2 Corinthians", 13));
        books.add(new BibleBook("Galatians", 6));
        books.add(new BibleBook("Ephesians", 6));
        books.add(new BibleBook("Philippians", 4));
        books.add(new BibleBook("Colossians", 4));
        books.add(new BibleBook("1 Thessalonians", 5));
        books.add(new BibleBook("2 Thessalonians", 3));
        books.add(new BibleBook("1 Timothy", 6));
        books.add(new BibleBook("2 Timothy", 4));
        books.add(new BibleBook("Titus", 3));
        books.add(new BibleBook("Philemon", 1));
        books.add(new BibleBook("Hebrews", 13));
        books.add(new BibleBook("James", 5));
        books.add(new BibleBook("1 Peter", 5));
        books.add(new BibleBook("2 Peter", 3));
        books.add(new BibleBook("1 John", 5));
        books.add(new BibleBook("2 John", 1));
        books.add(new BibleBook("3 John", 1));
        books.add(new BibleBook("Jude", 1));
        books.add(new BibleBook("Revelation", 22));
    }

    public int getTotalChapters() {

        return books.stream().mapToInt(BibleBook::chapters).sum();
    }

}
