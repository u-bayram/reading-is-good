package com.getir.readingisgood.dto;

import lombok.*;

import java.util.List;

/**
 * @author UmutBayram
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFilterResponseDto {
    private List<OrderInfoResponseDto> orders;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
}
