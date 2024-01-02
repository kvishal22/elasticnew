package com.kanna.elastic.repo;

import com.kanna.elastic.entity.Book;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends ElasticsearchRepository<Book,String> {

    List<Book> findByAuthorName(String authorName);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByTitleIgnoreCase(String title);
    @Query("{\"bool\": {\"should\": [{\"match\": {\"title\": {\"query\": \"?0\",\"fuzziness\": \"AUTO\"}}}, {\"match\": {\"authorName\": {\"query\": \"?0\",\"fuzziness\": \"AUTO\"}}}]}}")
    List<Book> autocompleteSuggestionsUsingQuery(String prefix);


}
