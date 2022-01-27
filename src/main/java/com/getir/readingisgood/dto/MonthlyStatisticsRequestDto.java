package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author UmutBayram
 */
@Getter
@Setter
public class MonthlyStatisticsRequestDto {
    @Email
    @NotEmpty
    private String email;
}
