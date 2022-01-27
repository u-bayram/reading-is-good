package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author UmutBayram
 */
@Getter
@Setter
public class UpdateBookResponseDto {
    private String code;
    private String name;
    private BigDecimal price;
    private Long stock;
}
