package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author UmutBayram
 */
@Getter
@Setter
public class OrderFilterRequestDto {
    @NotEmpty
    @Email
    private String email;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate finishDate;
    @NotEmpty
    @NumberFormat
    @Min(value = 0)
    private Integer page;
    @NotEmpty
    @NumberFormat
    @Min(value = 1)
    private Integer size;
}
