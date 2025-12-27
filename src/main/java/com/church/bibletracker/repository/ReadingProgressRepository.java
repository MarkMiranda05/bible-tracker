package com.church.bibletracker.repository;

import com.church.bibletracker.model.ReadingProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Long> {

    List<ReadingProgress> findByNameOrderByDateTimeReadAsc(String name);

}
