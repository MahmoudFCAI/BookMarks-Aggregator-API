package com.bookmarks.bookmarks_system.service;

import com.bookmarks.bookmarks_system.exception.MarkNotFoundException;
import com.bookmarks.bookmarks_system.exception.UserNotFoundException;
import com.bookmarks.bookmarks_system.model.dto.MarkDto;
import com.bookmarks.bookmarks_system.model.entity.Mark;
import com.bookmarks.bookmarks_system.model.entity.User;
import com.bookmarks.bookmarks_system.repository.MarkRepo;
import com.bookmarks.bookmarks_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarkService {

    @Autowired
    private MarkRepo markRepo;
    @Autowired
    private UserRepo userRepo;

    public MarkDto addBookMarkToUser(Integer userId, MarkDto mark) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userId + " not found"));

        Mark savedMark = markRepo.save(Mark.toEntity(mark, user));
        return MarkDto.toDto(savedMark);
    }

    public MarkDto updateBookMarkToUser(Integer userId, MarkDto mark) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userId + " not found"));

        Mark existMark = markRepo.findById(mark.getId())
                .orElseThrow(() -> new MarkNotFoundException("Mark with id = " + mark.getId() + " not found"));

        if (!existMark.getUser().getId().equals(userId)) {
            throw new MarkNotFoundException("This mark does not belong to user with id = " + userId);
        }

        existMark.setUrl(mark.getUrl());
        existMark.setName(mark.getName());
        existMark.setDes(mark.getDes());
        existMark.setType(mark.getType());
        existMark.setTag(mark.getTag());

        return MarkDto.toDto(markRepo.save(existMark));
    }

    public MarkDto getMarkById(Integer id) {
        Mark mark = markRepo.findById(id)
                .orElseThrow(() -> new MarkNotFoundException("Mark with id = " + id + " not found"));
        return MarkDto.toDto(mark);
    }

    public List<MarkDto> getAllMark() {
        return markRepo.findAll().stream()
                .map(MarkDto::toDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Integer id) {
        if (!markRepo.existsById(id)) {
            throw new MarkNotFoundException("Mark with id = " + id + " not found");
        }
        markRepo.deleteById(id);
    }

    public List<MarkDto> searchByTag(String tagName) {
        List<Mark> marks = markRepo.findByTag(tagName);

        if (marks.isEmpty()) {
            throw new MarkNotFoundException("No marks found with tag = " + tagName);
        }

        return marks.stream()
                .map(MarkDto::toDto)
                .toList();
    }

    public List<MarkDto> getMarksForUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userId + " not found"));

        List<Mark> marks = markRepo.findByUserId(userId);

        if (marks.isEmpty()) {
            throw new MarkNotFoundException("No marks found for user with id = " + userId);
        }

        return marks.stream()
                .map(MarkDto::toDto)
                .toList();
    }

}
