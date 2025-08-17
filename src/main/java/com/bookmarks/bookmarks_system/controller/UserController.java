package com.bookmarks.bookmarks_system.controller;


import com.bookmarks.bookmarks_system.model.dto.MarkDto;
import com.bookmarks.bookmarks_system.model.dto.UserDto;
import com.bookmarks.bookmarks_system.service.MarkService;
import com.bookmarks.bookmarks_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MarkService markService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAllUser();
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto user){
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Integer id, @RequestBody UserDto user){
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id){
        userService.deleteUser(id);
    }

    @GetMapping("/{userId}/marks")
    public List<MarkDto> getMarksForUser(@PathVariable Integer userId) {
        return markService.getMarksForUser(userId);
    }


}
