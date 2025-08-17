package com.bookmarks.bookmarks_system.model.entity;

import com.bookmarks.bookmarks_system.model.dto.MarkDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "marks")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String url;
    private String name;
    private String des;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "mark_tags",
            joinColumns = @JoinColumn(name = "mark_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public static Mark toEntity(MarkDto dto, User user, Set<Tag> tags) {
        return Mark.builder()
                .id(dto.getId())
                .url(dto.getUrl())
                .name(dto.getName())
                .des(dto.getDes())
                .type(dto.getType())
                .user(user)
                .tags(tags)
                .build();
    }
}

