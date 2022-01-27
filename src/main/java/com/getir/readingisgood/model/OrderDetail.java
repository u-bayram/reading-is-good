package com.getir.readingisgood.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author UmutBayram
 */
@Builder
@Getter
@Setter
public class OrderDetail {
    private String bookCode;
    private Integer count;
    private BigDecimal price;
}
