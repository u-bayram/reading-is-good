package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MonthlyStatisticsResponseDto {
    private List<MonthlyStatisticsDetailsResponseDto> reports;
}
