package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author UmutBayram
 */
@Getter
@Setter
public class CustomerOrdersResponseDto {
    private List<OrderInfoResponseDto> orders;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
}
