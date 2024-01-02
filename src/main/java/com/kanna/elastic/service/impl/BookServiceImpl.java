package com.kanna.elastic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.kanna.elastic.entity.Book;
import com.kanna.elastic.repo.BookRepo;
import com.kanna.elastic.service.BookService;
import com.kanna.elastic.service.exception.BookNotFoundException;
import com.kanna.elastic.service.exception.DuplicateIsbnException;
import com.kanna.elastic.util.ESUtil;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.match;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ElasticsearchClient elasticsearchClient;
    @Override
    public Optional<Book> getByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
         bookRepo.findAll().forEach(books::add);
         return books;
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        return bookRepo.findByAuthorName(authorName);
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        var criteria = QueryBuilders.bool(builder -> builder.must(
                match(queryAuthor -> queryAuthor.field("authorName").query(author)),
                match(queryTitle -> queryTitle.field("title").query(title))
        ));

        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Book.class)
                .stream().map(SearchHit::getContent).toList();
    }

    public List<Book> findByTitleAndAuthorFuzzy(String title, String author) {
        var criteria = QueryBuilders.bool(builder -> builder.must(
                match(queryAuthor -> queryAuthor.field("authorName").fuzziness("2")),
                match(queryTitle -> queryTitle.field("title").fuzziness("2"))
                ));
        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Book.class)
                .stream().map(SearchHit::getContent).toList();
    }

    @Override
    public Book create(Book book) throws DuplicateIsbnException {
        if (getByIsbn(book.getIsbn()).isEmpty()) {
            return bookRepo.save(book);
        }
        throw new DuplicateIsbnException(String.format("The provided ISBN: %s already exists. Use update instead!", book.getIsbn()));
    }
    @Override
    public void deleteById(String id) {
        bookRepo.deleteById(id);

    }

    @Override
    public Book update(String id, Book book) throws BookNotFoundException {
        Book oldBook = bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No book is associated with the given id"));
        oldBook.setIsbn(book.getIsbn());
        oldBook.setAuthorName(book.getAuthorName());
        oldBook.setPublicationYear(book.getPublicationYear());
        oldBook.setTitle(book.getTitle());
        return bookRepo.save(oldBook);
    }

    @Override
    public SearchResponse<Book> fuzzySearch(String fuzzyWord) throws IOException {
        Supplier<Query> querySupplier = ESUtil.getSupplier(fuzzyWord);
        SearchResponse<Book> response = elasticsearchClient
                .search(s->s.index("books").query(querySupplier.get()), Book.class);
        System.out.println(querySupplier.get().toString());
        return response;
    }
}
