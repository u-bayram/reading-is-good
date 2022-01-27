package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.MonthlyStatisticsRequestDto;
import com.getir.readingisgood.dto.MonthlyStatisticsResponseDto;
import com.getir.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final OrderService orderService;

    @Autowired
    public StatisticsController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/monthly")
    public ResponseEntity<MonthlyStatisticsResponseDto> monthlyStatistics(@RequestBody @Valid MonthlyStatisticsRequestDto monthlyStatisticsRequestDto) {
        return new ResponseEntity<MonthlyStatisticsResponseDto>(orderService.monthlyStatistics(monthlyStatisticsRequestDto), HttpStatus.OK);
    }
}
