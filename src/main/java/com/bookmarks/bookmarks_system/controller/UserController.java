package com.bookmarks.bookmarks_system.controller;


import com.bookmarks.bookmarks_system.model.dto.BookmarkDto;
import com.bookmarks.bookmarks_system.model.dto.UserDto;
import com.bookmarks.bookmarks_system.service.BookmarkService;
import com.bookmarks.bookmarks_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private BookmarkService markService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>>getAll(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){
        UserDto createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody UserDto user){
        return ResponseEntity.ok(userService.updateUser(id,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteUserById(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/Bookmarks")
    public ResponseEntity<List<BookmarkDto>>getMarksForUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(markService.getBookmarksForUser(userId));
    }

    @PostMapping("/{userId}/userAddBookmark")
    public ResponseEntity<BookmarkDto>addBookmark(@PathVariable Integer userId, @RequestBody BookmarkDto mark){
        BookmarkDto createdBookmark = markService.addBookmarkToUser(userId, mark);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookmark);
    }

    @PutMapping("/{id}/userUpdateBookmark/{userId}")
    public ResponseEntity<BookmarkDto> updateBookmark(@PathVariable Integer userId, @PathVariable Integer id, @RequestBody BookmarkDto mark) {
        mark.setId(id);
        return ResponseEntity.ok(markService.updateBookmarkToUser(userId, mark));
    }

}
