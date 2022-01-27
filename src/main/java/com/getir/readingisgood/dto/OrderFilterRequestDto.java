package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class OrderFilterRequestDto {
    private String email;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate finishDate;
    private Integer page;
    private Integer size;
}
