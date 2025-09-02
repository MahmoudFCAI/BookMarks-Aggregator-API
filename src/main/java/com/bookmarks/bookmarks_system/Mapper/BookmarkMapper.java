package com.bookmarks.bookmarks_system.Mapper;

import com.bookmarks.bookmarks_system.model.dto.BookmarkDto;
import com.bookmarks.bookmarks_system.model.entity.Bookmark;
import com.bookmarks.bookmarks_system.model.entity.Tag;
import com.bookmarks.bookmarks_system.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookmarkMapper {



    @Mapping(source = "user.id", target = "userId")
    BookmarkDto toDto(Bookmark bookmark);


    @Mapping(source = "userId", target = "user.id")
    Bookmark toEntity(BookmarkDto dto);


    default User map(Integer userId) {
        if (userId == null) return null;
        User user = new User();
        user.setId(userId);
        return user;
    }


    default Integer map(User user) {
        return (user == null) ? null : user.getId();
    }

    default String map(Tag tag) {
        return (tag == null) ? null : tag.getName();
    }


    default Tag map(String tagName) {
        if (tagName == null) return null;
        Tag tag = new Tag();
        tag.setName(tagName);
        return tag;
    }
}
