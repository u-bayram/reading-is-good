package com.getir.readingisgood.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author UmutBayram
 */
@Getter
@Setter
@Document(collection = "customer")
public class Customer {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;
    private String password;

    private String name;
    private String surname;
    @Version
    private Long version;

}
