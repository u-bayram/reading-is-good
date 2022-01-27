package com.getir.readingisgood.controller;


import com.getir.readingisgood.dto.NewOrderRequestDto;
import com.getir.readingisgood.dto.OrderFilterRequestDto;
import com.getir.readingisgood.dto.OrderFilterResponseDto;
import com.getir.readingisgood.dto.OrderInfoResponseDto;
import com.getir.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/newOrder")
    public ResponseEntity<OrderInfoResponseDto> newOrder(@Valid @RequestBody NewOrderRequestDto newOrderRequestDto) {
        return new ResponseEntity<OrderInfoResponseDto>(orderService.newOrder(newOrderRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderInfoResponseDto> orderInfo(@PathVariable String orderId) {
        return new ResponseEntity<OrderInfoResponseDto>(orderService.orderInfoById(orderId), HttpStatus.OK);
    }


    @PostMapping("/filter")
    public ResponseEntity<OrderFilterResponseDto> filter(@RequestBody OrderFilterRequestDto orderFilterRequestDto) {
        return new ResponseEntity<OrderFilterResponseDto>(orderService.filter(orderFilterRequestDto), HttpStatus.OK);
    }


}
