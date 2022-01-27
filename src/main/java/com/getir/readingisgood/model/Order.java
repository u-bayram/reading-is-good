package com.getir.readingisgood.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@Document(collection = "order")
public class Order {
    @Id
    private String id;
    private String email;
    private LocalDateTime date;

    private List<OrderDetail> orderDetails;
    @Version
    private Long version;
}
