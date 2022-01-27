package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CustomerOrdersRequestDto {
    private String email;
    private Integer page;
    private Integer size;
}
