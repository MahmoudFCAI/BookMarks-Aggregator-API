package com.bookmarks.bookmarks_system.service;

import com.bookmarks.bookmarks_system.Mapper.UserMapper;
import com.bookmarks.bookmarks_system.exception.ResourceNotFoundException;
import com.bookmarks.bookmarks_system.model.dto.UserDto;
import com.bookmarks.bookmarks_system.model.entity.User;
import com.bookmarks.bookmarks_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired private UserRepo userRepo;
    @Autowired private UserMapper userMapper;



    public UserDto getUserById(Integer id){
        return this.userRepo.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    public List<UserDto> getAllUser(){
        return this.userRepo.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto saveUser(UserDto userDto){
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(this.userRepo.save(user));
    }

    public UserDto updateUser(Integer id, UserDto userDto){

        if(userDto.getId() != null && !userDto.getId().equals(id))
            throw new ResourceNotFoundException("ID in request body does not match path ID");

        User existingUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPhone(userDto.getPhone());
        existingUser.setPassword(userDto.getPassword());

        return userMapper.toDto(userRepo.save(existingUser));

    }

    public void deleteUser(Integer id){
        if(!userRepo.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        this.userRepo.deleteById(id);
    }
}
