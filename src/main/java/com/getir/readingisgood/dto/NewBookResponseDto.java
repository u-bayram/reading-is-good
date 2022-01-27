package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class NewBookResponseDto {
    private String code;
    private String name;
    private BigDecimal price;
    private Long stock;
}
