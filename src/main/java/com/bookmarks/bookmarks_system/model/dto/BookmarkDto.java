package com.bookmarks.bookmarks_system.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BookmarkDto {

    private Integer id;
    private String url;
    private String name;
    private String des;
    private String type;
    private Set<String> tags;
    private Integer userId;


}

