package com.getir.readingisgood.dto;

import com.getir.readingisgood.model.OrderDetail;

import java.time.LocalDateTime;
import java.util.List;

public class OrderInfoResponseDto {

    private String id;
    private LocalDateTime date;
    private String email;

    private List<OrderDetail> orderDetails;

}
