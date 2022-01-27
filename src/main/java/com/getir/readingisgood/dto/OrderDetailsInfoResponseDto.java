package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDetailsInfoResponseDto {
    private String bookCode;
    private Integer count;
    private BigDecimal price;
}
