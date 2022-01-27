package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.CustomerOrdersRequestDto;
import com.getir.readingisgood.dto.CustomerOrdersResponseDto;
import com.getir.readingisgood.dto.NewCustomerRequestDto;
import com.getir.readingisgood.dto.NewCustomerResponseDto;

public interface CustomerService  {

    NewCustomerResponseDto newCustomer(NewCustomerRequestDto newCustomerRequestDto);

}
