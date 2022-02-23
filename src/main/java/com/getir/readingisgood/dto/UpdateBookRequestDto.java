package com.getir.readingisgood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
/**
 * @author UmutBayram
 */
@Builder
@Getter
@Setter
public class UpdateBookRequestDto {
    @NotNull
    @NumberFormat
    @Min(value = 0)
    private BigDecimal price;
    @NotNull
    @NumberFormat
    @Min(value = 0)
    private Long stock;
}
