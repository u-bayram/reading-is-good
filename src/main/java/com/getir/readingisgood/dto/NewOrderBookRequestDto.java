package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author UmutBayram
 */
@Getter
@Setter
public class NewOrderBookRequestDto {
    @NotEmpty
    private String bookCode;
    @NotEmpty
    @NumberFormat
    @Min(value = 1)
    private Integer count;
}
