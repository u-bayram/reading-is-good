package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MonthlyStatisticsDetailsResponseDto {
    private Integer monthNumber;
    private String month;
    private Long orderCount;
    private Long bookCount;
    private BigDecimal totalPrice;
}
