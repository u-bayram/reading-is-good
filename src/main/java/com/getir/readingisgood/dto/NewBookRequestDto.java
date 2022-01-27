package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class NewBookRequestDto {
    @NotBlank
    @NotNull
    private String code;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Long stock;
}
