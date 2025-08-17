package com.bookmarks.bookmarks_system.service;

import com.bookmarks.bookmarks_system.exception.MarkNotFoundException;
import com.bookmarks.bookmarks_system.exception.UserNotFoundException;
import com.bookmarks.bookmarks_system.model.dto.MarkDto;
import com.bookmarks.bookmarks_system.model.entity.Mark;
import com.bookmarks.bookmarks_system.model.entity.Tag;
import com.bookmarks.bookmarks_system.model.entity.User;
import com.bookmarks.bookmarks_system.repository.MarkRepo;
import com.bookmarks.bookmarks_system.repository.TagRepo;
import com.bookmarks.bookmarks_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MarkService {

    @Autowired private MarkRepo markRepo;
    @Autowired private UserRepo userRepo;
    @Autowired private TagRepo tagRepo;

    public MarkDto addBookMarkToUser(Integer userId, MarkDto markDto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userId + " not found"));

        Set<Tag> tags = resolveTags(markDto.getTags());
        Mark saved = markRepo.save(Mark.toEntity(markDto, user, tags));
        return MarkDto.toDto(saved);
    }

    public MarkDto updateBookMarkToUser(Integer userId, MarkDto markDto) {
        userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userId + " not found"));

        Mark existMark = markRepo.findById(markDto.getId())
                .orElseThrow(() -> new MarkNotFoundException("Mark with id = " + markDto.getId() + " not found"));

        if (!existMark.getUser().getId().equals(userId)) {
            throw new MarkNotFoundException("This mark does not belong to user with id = " + userId);
        }

        existMark.setUrl(markDto.getUrl());
        existMark.setName(markDto.getName());
        existMark.setDes(markDto.getDes());
        existMark.setType(markDto.getType());
        existMark.setTags(resolveTags(markDto.getTags()));

        return MarkDto.toDto(markRepo.save(existMark));
    }

    public MarkDto getMarkById(Integer id) {
        Mark mark = markRepo.findById(id)
                .orElseThrow(() -> new MarkNotFoundException("Mark with id = " + id + " not found"));
        return MarkDto.toDto(mark);
    }

    public List<MarkDto> getAllMark() {
        return markRepo.findAll().stream().map(MarkDto::toDto).toList();
    }

    public void deleteById(Integer id) {
        if (!markRepo.existsById(id))
            throw new MarkNotFoundException("Mark with id = " + id + " not found");
        markRepo.deleteById(id);
    }

    public List<MarkDto> searchByTag(String tagName) {
        List<Mark> marks = markRepo.findByTags_Name(tagName);
        if (marks.isEmpty())
            throw new MarkNotFoundException("No marks found with tag = " + tagName);
        return marks.stream().map(MarkDto::toDto).toList();
    }

    public List<MarkDto> getMarksForUser(Integer userId){
        return markRepo.findByUserId(userId).stream().map(MarkDto::toDto).toList();
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
