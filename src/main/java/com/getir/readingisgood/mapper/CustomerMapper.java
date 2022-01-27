package com.getir.readingisgood.mapper;

import com.getir.readingisgood.dto.NewCustomerRequestDto;
import com.getir.readingisgood.dto.NewCustomerResponseDto;
import com.getir.readingisgood.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author UmutBayram
 */
@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "name", source = "newCustomerRequestDto.name")
    Customer customerToNewCustomerRequestDto(NewCustomerRequestDto newCustomerRequestDto);

    NewCustomerResponseDto newCustomerResponseDtoToCustomer(Customer customer);
}