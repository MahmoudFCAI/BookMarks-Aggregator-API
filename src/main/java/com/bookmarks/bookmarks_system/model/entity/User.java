package com.bookmarks.bookmarks_system.model.entity;


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
    private List<Bookmark> Bookmarks = new ArrayList<>();


}
