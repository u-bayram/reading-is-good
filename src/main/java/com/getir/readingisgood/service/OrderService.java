package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.*;

import java.util.List;

public interface OrderService {
    OrderInfoResponseDto newOrder(NewOrderRequestDto newOrderRequestDto);

    OrderInfoResponseDto orderInfoById(String orderId);

    OrderFilterResponseDto filter(OrderFilterRequestDto orderFilterRequestDto);

    MonthlyStatisticsResponseDto monthlyStatistics(MonthlyStatisticsRequestDto monthlyStatisticsRequestDto);

    CustomerOrdersResponseDto orders(CustomerOrdersRequestDto customerOrdersRequestDto);
}
