package com.getir.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author UmutBayram
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto implements Serializable {
    @Email
    @NotEmpty
    private String email;
    @NotBlank
    @NotEmpty
    private String password;
}
