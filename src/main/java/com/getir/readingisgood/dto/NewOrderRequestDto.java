package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author UmutBayram
 */
@Getter
@Setter
public class NewOrderRequestDto {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private List<NewOrderBookRequestDto> books;
}
