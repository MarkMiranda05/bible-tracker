package com.church.bibletracker.controller;

import com.church.bibletracker.config.BibleConfig;
import com.church.bibletracker.model.CurrentReading;
import com.church.bibletracker.model.ReadingProgress;
import com.church.bibletracker.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BibleTrackerController {

    private final ProgressService progressService;

    private final BibleConfig bibleConfig;

    public BibleTrackerController(ProgressService progressService, BibleConfig bibleConfig) {

        this.progressService = progressService;
        this.bibleConfig = bibleConfig;
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/api/users")
    @ResponseBody
    public List<String> getUsers() {

        return progressService.getAllUsers();
    }

    @GetMapping("/api/current-reading/{name}")
    @ResponseBody
    public CurrentReading getCurrentReading(@PathVariable String name) {

        return progressService.getCurrentReading(name);
    }

    @PostMapping("/api/mark-read")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> markAsRead(@RequestBody Map<String, Object> body) {

        String name = (String) body.get("name");
        String book = (String) body.get("book");
        int chapter = (int) body.get("chapter");

        progressService.markAsRead(name, book, chapter);

        CurrentReading nextReading = progressService.getCurrentReading(name);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("nextBook", nextReading.book());
        response.put("nextChapter", nextReading.chapter());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/progress/{name}")
    @ResponseBody
    public List<ReadingProgress> getProgress(@PathVariable String name) {

        return progressService.getProgressByName(name);
    }

    @GetMapping("/api/stats/{name}")
    @ResponseBody
    public Map<String, Object> getStats(@PathVariable String name) {

        List<ReadingProgress> progress = progressService.getProgressByName(name);
        int totalChapters = bibleConfig.getTotalChapters();

        Map<String, Object> stats = new HashMap<>();
        stats.put("chaptersRead", progress.size());
        stats.put("totalChapters", totalChapters);
        stats.put("percentComplete", Math.round((progress.size() * 100.0) / totalChapters * 10) / 10.0);

        return stats;
    }

}
