package com.bookmarks.bookmarks_system.controller;

import com.bookmarks.bookmarks_system.model.dto.BookmarkDto;
import com.bookmarks.bookmarks_system.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;



@RestController
@RequestMapping("/api/v1/Bookmarks")

public class BookmarkController {

    @Autowired
    private BookmarkService BookmarkService;

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(BookmarkService.getBookmarkById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookmarkDto>>getAll(){
        return ResponseEntity.ok(BookmarkService.getAllBookmark());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        BookmarkService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookmarkDto>>searchByTag(@RequestParam String tag){
        return ResponseEntity.ok(BookmarkService.searchByTag(tag));
    }


}
