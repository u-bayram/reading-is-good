package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author UmutBayram
 */
@Getter
@Setter
public class OrderDetailsInfoResponseDto {
    private String bookCode;
    private Integer count;
    private BigDecimal price;
}
