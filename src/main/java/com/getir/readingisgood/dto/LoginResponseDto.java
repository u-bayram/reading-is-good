package com.getir.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author UmutBayram
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto implements Serializable {
    private String token;
}
