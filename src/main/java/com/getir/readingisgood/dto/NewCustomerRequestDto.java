package com.getir.readingisgood.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author UmutBayram
 */
@Builder
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
