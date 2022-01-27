package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author UmutBayram
 */
@Getter
@Setter
public class CustomerOrdersRequestDto {
    @NotEmpty
    @Email
    private String email;
    @NumberFormat
    @NotNull
    private Integer page;
    @NumberFormat
    @NotNull
    private Integer size;

}
