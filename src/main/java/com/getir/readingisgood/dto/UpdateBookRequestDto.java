package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateBookRequestDto {
    private String code;
    private BigDecimal price;
    private Long stock;
}
