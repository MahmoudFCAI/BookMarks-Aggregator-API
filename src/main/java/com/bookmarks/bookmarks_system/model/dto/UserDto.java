package com.bookmarks.bookmarks_system.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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

}
