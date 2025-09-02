package com.bookmarks.bookmarks_system.service;

import com.bookmarks.bookmarks_system.Mapper.BookmarkMapper;
import com.bookmarks.bookmarks_system.exception.MarkNotFoundException;
import com.bookmarks.bookmarks_system.exception.UserNotFoundException;
import com.bookmarks.bookmarks_system.model.dto.BookmarkDto;
import com.bookmarks.bookmarks_system.model.entity.Bookmark;
import com.bookmarks.bookmarks_system.model.entity.Tag;
import com.bookmarks.bookmarks_system.model.entity.User;
import com.bookmarks.bookmarks_system.repository.BookmarkRepo;
import com.bookmarks.bookmarks_system.repository.TagRepo;
import com.bookmarks.bookmarks_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

    @Autowired private BookmarkRepo BookmarkRepo;
    @Autowired private UserRepo userRepo;
    @Autowired private TagRepo tagRepo;
    @Autowired private BookmarkMapper bookmarkMapper;


    public BookmarkDto getBookmarkById(Integer id) {
        Bookmark mark = BookmarkRepo.findById(id)
                .orElseThrow(() -> new MarkNotFoundException("Mark with id = " + id + " not found"));
        return bookmarkMapper.toDto(mark);
    }

    public List<BookmarkDto> getAllBookmark() {
        return BookmarkRepo.findAll().stream().map(bookmarkMapper::toDto).toList();
    }

    public void deleteById(Integer id) {
        if (!BookmarkRepo.existsById(id))
            throw new MarkNotFoundException("Mark with id = " + id + " not found");
        BookmarkRepo.deleteById(id);
    }

    public List<BookmarkDto> searchByTag(String tagName) {
        List<Bookmark> marks = BookmarkRepo.findByTags_Name(tagName);
        if (marks.isEmpty())
            throw new MarkNotFoundException("No marks found with tag = " + tagName);
        return marks.stream().map(bookmarkMapper::toDto).toList();
    }


    public List<BookmarkDto> getBookmarksForUser(Integer userId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id = " + userId + " not found"));

        List<BookmarkDto> bookmarks = BookmarkRepo.findByUserId(userId)
                .stream()
                .map(bookmarkMapper::toDto)
                .toList();

        if (bookmarks.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " has no bookmarks.");
        }

        return bookmarks;
    }


    public BookmarkDto addBookmarkToUser(Integer userId, BookmarkDto markDto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userId + " not found"));

        Bookmark bookmark = bookmarkMapper.toEntity(markDto);
        bookmark.setUser(user);
        bookmark.setTags(resolveTags(markDto.getTags()));

        Bookmark saved = BookmarkRepo.save(bookmark);
        return bookmarkMapper.toDto(saved);
    }

    public BookmarkDto updateBookmarkToUser(Integer userId, BookmarkDto markDto) {
        userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userId + " not found"));

        Bookmark existMark = BookmarkRepo.findById(markDto.getId())
                .orElseThrow(() -> new MarkNotFoundException("Mark with id = " + markDto.getId() + " not found"));

        if (!existMark.getUser().getId().equals(userId)) {
            throw new MarkNotFoundException("This mark does not belong to user with id = " + userId);
        }

        existMark.setUrl(markDto.getUrl());
        existMark.setName(markDto.getName());
        existMark.setDes(markDto.getDes());
        existMark.setType(markDto.getType());
        existMark.setTags(resolveTags(markDto.getTags()));

        return bookmarkMapper.toDto(BookmarkRepo.save(existMark));
    }



    private Set<Tag> resolveTags(Set<String> tagNames){
        if (tagNames == null) return new HashSet<>();
        return tagNames.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> s.toLowerCase())
                .map(name -> tagRepo.findByName(name)
                        .orElseGet(() -> tagRepo.save(Tag.builder().name(name).build())))
                .collect(Collectors.toSet());
    }

}
