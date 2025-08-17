package com.bookmarks.bookmarks_system.repository;



import com.bookmarks.bookmarks_system.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TagRepo extends JpaRepository<Tag, Integer> {
    Optional<Tag> findByName(String name);
}

