package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.NewCustomerRequestDto;
import com.getir.readingisgood.dto.NewCustomerResponseDto;

/**
 * @author UmutBayram
 */
public interface CustomerService {

    NewCustomerResponseDto newCustomer(NewCustomerRequestDto newCustomerRequestDto);

}
