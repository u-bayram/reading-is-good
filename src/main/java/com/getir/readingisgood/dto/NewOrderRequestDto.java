package com.getir.readingisgood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author UmutBayram
 */
@Builder
@Getter
@Setter
public class NewOrderRequestDto {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private List<NewOrderBookRequestDto> books;
}
