package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewOrderRequestDto {
    private String email;
    private List<NewOrderBookRequestDto> books;
}
