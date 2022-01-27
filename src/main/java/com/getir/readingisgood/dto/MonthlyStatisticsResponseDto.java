package com.getir.readingisgood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author UmutBayram
 */
@Builder
@Getter
@Setter
public class MonthlyStatisticsResponseDto {
    private List<MonthlyStatisticsDetailsResponseDto> reports;
}
