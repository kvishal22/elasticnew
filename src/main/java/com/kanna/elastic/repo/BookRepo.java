package com.kanna.elastic.repo;

import com.kanna.elastic.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends ElasticsearchRepository<Book,String> {

    List<Book> findByAuthorName(String authorName);
    Optional<Book> findByIsbn(String isbn);
}
