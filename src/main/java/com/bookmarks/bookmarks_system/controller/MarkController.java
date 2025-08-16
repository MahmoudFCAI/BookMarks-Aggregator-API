package com.bookmarks.bookmarks_system.controller;


import com.bookmarks.bookmarks_system.model.dto.MarkDto;
import com.bookmarks.bookmarks_system.model.entity.Mark;
import com.bookmarks.bookmarks_system.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marks")
public class MarkController {

    @Autowired
    private MarkService markService;

    @PostMapping("/users/{userId}")
    public MarkDto addBookMark(@PathVariable Integer userId, @RequestBody MarkDto mark){
        return markService.addBookMarkToUser(userId,mark);
    }

    @PutMapping("/{id}/users/{userId}")
    public MarkDto updateBookMark(@PathVariable Integer userId, @PathVariable Integer id, @RequestBody MarkDto mark) {
        mark.setId(id);
        return markService.updateBookMarkToUser(userId, mark);
    }

    @GetMapping("/{id}")
    public MarkDto getById(@PathVariable Integer id){
        return markService.getMarkById(id);
    }

    @GetMapping
    public List<MarkDto> getAll(){
        return markService.getAllMark();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        markService.deleteById(id);
    }

    @GetMapping("/search")
    public List<MarkDto> searchByTag(@RequestParam String tag){
        return markService.searchByTag(tag);
    }

}
