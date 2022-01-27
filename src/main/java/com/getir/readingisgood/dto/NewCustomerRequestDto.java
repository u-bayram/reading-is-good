package com.getir.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCustomerRequestDto {
    @Email
    private String email;

    private String name;
    private String surname;
}
