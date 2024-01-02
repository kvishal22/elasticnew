package com.kanna.elastic.service;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.kanna.elastic.entity.Book;
import com.kanna.elastic.service.exception.BookNotFoundException;
import com.kanna.elastic.service.exception.DuplicateIsbnException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> getByIsbn(String isbn);

    List<Book> getAll();

    List<Book> findByAuthor(String authorName);

    List<Book> findByTitleAndAuthor(String title, String author);

    Book create(Book book) throws DuplicateIsbnException;

    void deleteById(String id);

    Book update(String id, Book book) throws BookNotFoundException;
    SearchResponse<Book> fuzzySearch(String fuzzyWord) throws IOException;
     List<Book> autocompleteSuggestions(String prefix);
    List<Book> autocompleteSuggestionsUsingQuery(String prefix);
}
