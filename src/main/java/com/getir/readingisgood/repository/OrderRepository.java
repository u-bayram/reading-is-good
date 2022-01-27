package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Page<Order> findByEmail(String email, Pageable pageable);

    Page<Order> findByEmailAndDateGreaterThanEqualAndDateLessThanEqual(String email, LocalDate startDate, LocalDate finishDate, Pageable pageable);

    List<Book> findByOrderDetailsBookCodeIn(List<String> bookCodes);
}
