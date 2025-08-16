package com.bookmarks.bookmarks_system.repository;

import com.bookmarks.bookmarks_system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
