package com.bookmarks.bookmarks_system.model.dto;

import com.bookmarks.bookmarks_system.model.entity.Mark;
import com.bookmarks.bookmarks_system.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.stream.Collectors;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MarkDto {

    private Integer id;
    private String url;
    private String name;
    private String des;
    private String type;
    private Set<String> tags;
    private Integer userId;

    public static MarkDto toDto(Mark entity){
        return MarkDto.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .name(entity.getName())
                .des(entity.getDes())
                .type(entity.getType())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .tags(entity.getTags() != null ? entity.getTags().stream().map(Tag::getName).collect(Collectors.toSet()) : null)
                .build();
    }
}

