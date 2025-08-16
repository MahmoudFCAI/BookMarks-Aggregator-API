package com.bookmarks.bookmarks_system.model.dto;


import com.bookmarks.bookmarks_system.model.entity.Mark;
import com.bookmarks.bookmarks_system.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private String password;


    public static UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .password(entity.getPassword())
                .build();
    }

}
