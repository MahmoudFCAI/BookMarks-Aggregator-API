package com.bookmarks.bookmarks_system.repository;

import com.bookmarks.bookmarks_system.model.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MarkRepo extends JpaRepository<Mark,Integer> {

    List<Mark> findByUserId(Integer userId);
    List<Mark> findByTags_Name(String tagName);

}
