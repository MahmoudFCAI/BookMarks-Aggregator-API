package com.bookmarks.bookmarks_system.model.entity;


import com.bookmarks.bookmarks_system.model.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@Table (name = "users")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String email;
    private String phone;
    private String password;

    @OneToMany (mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mark> marks = new ArrayList<>();


    public static User toEntity(UserDto dto){
        User user = User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .build();
        return user;
    }
}
