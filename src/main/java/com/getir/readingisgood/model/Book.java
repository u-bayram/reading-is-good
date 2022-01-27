package com.getir.readingisgood.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * @author UmutBayram
 */
@Getter
@Setter
@Document(collection = "book")
public class Book {
    @Id
    private String id;
    @Indexed(unique = true)
    private String code;
    private String name;
    private BigDecimal price;
    private Long stock;
    @Version
    private Long version;
}
