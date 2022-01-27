package com.getir.readingisgood.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author UmutBayram
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewBookResponseDto {
    private String code;
    private String name;
    private BigDecimal price;
    private Long stock;
}
