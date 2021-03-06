package com.getir.readingisgood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author UmutBayram
 */
@Builder
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
    @NotNull
    private String sortField;
    @NotNull
    private String sortDir;
}
