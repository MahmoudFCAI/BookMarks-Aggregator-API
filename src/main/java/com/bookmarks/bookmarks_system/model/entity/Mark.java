package com.bookmarks.bookmarks_system.model.entity;


import com.bookmarks.bookmarks_system.model.dto.MarkDto;
import com.bookmarks.bookmarks_system.model.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table (name = "marks")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    private String name;
    private String des;
    private String type;
    private String tag;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public static Mark toEntity(MarkDto dto, User user){
        return Mark.builder()
                .id(dto.getId())
                .url(dto.getUrl())
                .name(dto.getName())
                .des(dto.getDes())
                .type(dto.getType())
                .tag(dto.getTag())
                .user(user)
                .build();
    }

}
