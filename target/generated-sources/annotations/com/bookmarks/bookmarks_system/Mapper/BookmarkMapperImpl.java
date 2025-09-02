package com.bookmarks.bookmarks_system.Mapper;

import com.bookmarks.bookmarks_system.model.dto.BookmarkDto;
import com.bookmarks.bookmarks_system.model.entity.Bookmark;
import com.bookmarks.bookmarks_system.model.entity.Tag;
import com.bookmarks.bookmarks_system.model.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-02T15:47:13+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class BookmarkMapperImpl implements BookmarkMapper {

    @Override
    public BookmarkDto toDto(Bookmark bookmark) {
        if ( bookmark == null ) {
            return null;
        }

        BookmarkDto.BookmarkDtoBuilder bookmarkDto = BookmarkDto.builder();

        bookmarkDto.userId( bookmarkUserId( bookmark ) );
        bookmarkDto.id( bookmark.getId() );
        bookmarkDto.url( bookmark.getUrl() );
        bookmarkDto.name( bookmark.getName() );
        bookmarkDto.des( bookmark.getDes() );
        bookmarkDto.type( bookmark.getType() );
        bookmarkDto.tags( tagSetToStringSet( bookmark.getTags() ) );

        return bookmarkDto.build();
    }

    @Override
    public Bookmark toEntity(BookmarkDto dto) {
        if ( dto == null ) {
            return null;
        }

        Bookmark.BookmarkBuilder bookmark = Bookmark.builder();

        bookmark.user( bookmarkDtoToUser( dto ) );
        bookmark.id( dto.getId() );
        bookmark.url( dto.getUrl() );
        bookmark.name( dto.getName() );
        bookmark.des( dto.getDes() );
        bookmark.type( dto.getType() );
        bookmark.tags( stringSetToTagSet( dto.getTags() ) );

        return bookmark.build();
    }

    private Integer bookmarkUserId(Bookmark bookmark) {
        if ( bookmark == null ) {
            return null;
        }
        User user = bookmark.getUser();
        if ( user == null ) {
            return null;
        }
        Integer id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<String> tagSetToStringSet(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = new LinkedHashSet<String>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Tag tag : set ) {
            set1.add( map( tag ) );
        }

        return set1;
    }

    protected User bookmarkDtoToUser(BookmarkDto bookmarkDto) {
        if ( bookmarkDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( bookmarkDto.getUserId() );

        return user.build();
    }

    protected Set<Tag> stringSetToTagSet(Set<String> set) {
        if ( set == null ) {
            return null;
        }

        Set<Tag> set1 = new LinkedHashSet<Tag>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( String string : set ) {
            set1.add( map( string ) );
        }

        return set1;
    }
}
