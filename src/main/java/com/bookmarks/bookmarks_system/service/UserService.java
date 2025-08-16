package com.bookmarks.bookmarks_system.service;

import com.bookmarks.bookmarks_system.exception.ResourceNotFoundException;
import com.bookmarks.bookmarks_system.model.dto.UserDto;
import com.bookmarks.bookmarks_system.model.entity.User;
import com.bookmarks.bookmarks_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDto getUserById(Integer id){
        return this.userRepo.findById(id)
                .map(UserDto::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    public List<UserDto> getAllUser(){
        return this.userRepo.findAll()
                .stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }

    public UserDto saveUser(UserDto user){
        return UserDto.toDto(this.userRepo.save(User.toEntity(user)));
    }

    public void deleteUser(Integer id){
        if(!userRepo.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        this.userRepo.deleteById(id);
    }
}
