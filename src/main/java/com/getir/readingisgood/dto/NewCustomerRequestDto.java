package com.getir.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author UmutBayram
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCustomerRequestDto {
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
}
