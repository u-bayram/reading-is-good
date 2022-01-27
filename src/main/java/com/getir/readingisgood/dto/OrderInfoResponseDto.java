package com.getir.readingisgood.dto;

import com.getir.readingisgood.model.OrderDetail;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author UmutBayram
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoResponseDto {

    private String id;
    private LocalDateTime date;
    private String email;

    private List<OrderDetailsInfoResponseDto> orderDetails;

}
