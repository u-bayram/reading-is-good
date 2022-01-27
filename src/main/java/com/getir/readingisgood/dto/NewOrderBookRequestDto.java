package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOrderBookRequestDto {
    private String bookCode;
    private Integer count;
}
