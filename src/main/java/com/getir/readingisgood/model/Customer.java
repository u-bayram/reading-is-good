package com.getir.readingisgood.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author UmutBayram
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
