package com.bookmarks.bookmarks_system.Mapper;

import com.bookmarks.bookmarks_system.model.dto.UserDto;
import com.bookmarks.bookmarks_system.model.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
