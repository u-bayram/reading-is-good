package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Page<Order> findByEmail(String email, Pageable pageable);

    @Query("{'email' : ?0 ,'date' : { $gte: ?1, $lte: ?2 } }")
    Page<Order> findByEmailAndDateBetween(String email, LocalDate startDate, LocalDate finishDate, Pageable pageable);

    @Query("{'email' : ?0 ,'date' : { $gte: ?1, $lte: ?2 } }")
    List<Order> findByEmailAndDateBetween(String email, LocalDate startDate, LocalDate finishDate);
}
