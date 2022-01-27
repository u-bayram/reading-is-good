package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.MonthlyStatisticsRequestDto;
import com.getir.readingisgood.dto.MonthlyStatisticsResponseDto;
import com.getir.readingisgood.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author UmutBayram
 */
@ExtendWith(MockitoExtension.class)
class StatisticsControllerTest {

    private StatisticsController statisticsController;

    @Mock
    private OrderService orderService;

    private MonthlyStatisticsResponseDto monthlyStatisticsResponseDto;
    private MonthlyStatisticsRequestDto monthlyStatisticsRequestDto;

    @BeforeEach
    void setUp() {
        statisticsController = new StatisticsController(orderService);
        monthlyStatisticsResponseDto = MonthlyStatisticsResponseDto.builder().build();
        monthlyStatisticsRequestDto = MonthlyStatisticsRequestDto.builder().build();
    }

    @Test
    void testMonthlyStatistics() {
        Mockito.when(orderService.monthlyStatistics(any(MonthlyStatisticsRequestDto.class))).thenReturn(monthlyStatisticsResponseDto);
        ResponseEntity<MonthlyStatisticsResponseDto> monthlyStatisticsResponseDtoResponseEntity = statisticsController.monthlyStatistics(monthlyStatisticsRequestDto);
        Assertions.assertNotNull(monthlyStatisticsResponseDtoResponseEntity);
    }
}