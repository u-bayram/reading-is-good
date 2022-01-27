package com.getir.readingisgood.dto;

import lombok.*;

/**
 * @author UmutBayram
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCustomerResponseDto {
    private String email;
    private String name;
    private String surname;
}
