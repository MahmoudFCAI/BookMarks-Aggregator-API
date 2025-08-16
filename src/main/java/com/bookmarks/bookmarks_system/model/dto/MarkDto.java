package com.bookmarks.bookmarks_system.model.dto;

import com.bookmarks.bookmarks_system.model.entity.Mark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String tag;
    private Integer userId;

    public static MarkDto toDto(Mark entity){
        return MarkDto.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .name(entity.getName())
                .des(entity.getDes())
                .type(entity.getType())
                .tag(entity.getTag())
                .userId(entity.getUser() != null? entity.getUser().getId(): null)
                .build();
    }


}
