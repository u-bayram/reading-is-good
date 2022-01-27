package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author UmutBayram
 */
@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByCode(String code);

    List<Book> findByCodeIn(List<String> codes);
}
