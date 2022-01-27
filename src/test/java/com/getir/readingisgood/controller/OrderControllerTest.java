package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.NewOrderRequestDto;
import com.getir.readingisgood.dto.OrderFilterRequestDto;
import com.getir.readingisgood.dto.OrderFilterResponseDto;
import com.getir.readingisgood.dto.OrderInfoResponseDto;
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
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    private OrderController orderController;

    @Mock
    private OrderService orderService;

    private OrderInfoResponseDto orderInfoResponseDto;
    private NewOrderRequestDto newOrderRequestDto;
    private OrderFilterResponseDto orderFilterResponseDto;
    private OrderFilterRequestDto orderFilterRequestDto;

    @BeforeEach
    void setUp() {
        orderController = new OrderController(orderService);
        orderInfoResponseDto = OrderInfoResponseDto.builder().build();
        newOrderRequestDto = NewOrderRequestDto.builder().build();
        orderFilterResponseDto = OrderFilterResponseDto.builder().build();
        orderFilterRequestDto = OrderFilterRequestDto.builder().build();
    }

    @Test
    void testNewOrder() {
        Mockito.when(orderService.newOrder(any(NewOrderRequestDto.class))).thenReturn(orderInfoResponseDto);
        ResponseEntity<OrderInfoResponseDto> newCustomerResponseDtoResponseEntity = orderController.newOrder(newOrderRequestDto);
        Assertions.assertNotNull(newCustomerResponseDtoResponseEntity);
    }

    @Test
    void testOrderInfo() {
        Mockito.when(orderService.orderInfoById(anyString())).thenReturn(orderInfoResponseDto);
        ResponseEntity<OrderInfoResponseDto> newCustomerResponseDtoResponseEntity = orderController.orderInfo("orderId");
        Assertions.assertNotNull(newCustomerResponseDtoResponseEntity);
    }

    @Test
    void testFilter() {
        Mockito.when(orderService.filter(any(OrderFilterRequestDto.class))).thenReturn(orderFilterResponseDto);
        ResponseEntity<OrderFilterResponseDto> orderFilterResponseDtoResponseEntity = orderController.filter(orderFilterRequestDto);
        Assertions.assertNotNull(orderFilterResponseDtoResponseEntity);
    }
}