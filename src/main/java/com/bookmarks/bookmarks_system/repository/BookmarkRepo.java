package com.bookmarks.bookmarks_system.repository;

import com.bookmarks.bookmarks_system.model.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface BookmarkRepo extends JpaRepository<Bookmark,Integer>  {

    List<Bookmark> findByUserId(Integer userId);
    List<Bookmark> findByTags_Name(String tagName);

}
