package com.getir.readingisgood.dto;

import com.getir.readingisgood.model.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author UmutBayram
 */
@Getter
@Setter
public class OrderInfoResponseDto {

    private String id;
    private LocalDateTime date;
    private String email;

    private List<OrderDetailsInfoResponseDto> orderDetails;

}
