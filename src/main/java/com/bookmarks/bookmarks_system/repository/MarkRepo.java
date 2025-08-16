package com.bookmarks.bookmarks_system.repository;

import com.bookmarks.bookmarks_system.model.entity.Mark;
import com.bookmarks.bookmarks_system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepo extends JpaRepository<Mark,Integer> {
    List<Mark> findByTag(String tag);
    List<Mark> findByUserId(Integer userId);
}
