package com.bookmarks.bookmarks_system.exception;

public class MarkNotFoundException extends ResourceNotFoundException {
    public MarkNotFoundException(String message) {
        super(message);
    }
}
