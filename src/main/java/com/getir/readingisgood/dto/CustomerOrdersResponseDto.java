package com.getir.readingisgood.dto;

import lombok.*;

import java.util.List;

/**
 * @author UmutBayram
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrdersResponseDto {
    private List<OrderInfoResponseDto> orders;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
}
