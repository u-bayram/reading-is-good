package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.CustomerOrdersRequestDto;
import com.getir.readingisgood.dto.CustomerOrdersResponseDto;
import com.getir.readingisgood.dto.NewCustomerRequestDto;
import com.getir.readingisgood.dto.NewCustomerResponseDto;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private CustomerController customerController;

    @Mock
    private CustomerService customerService;
    @Mock
    private OrderService orderService;

    private NewCustomerRequestDto newCustomerRequestDto;
    private CustomerOrdersRequestDto customerOrdersRequestDto;
    private NewCustomerResponseDto newCustomerResponseDto;
    private CustomerOrdersResponseDto customerOrdersResponseDto;

    @BeforeEach
    void setUp() {
        customerController = new CustomerController(customerService, orderService);
        newCustomerRequestDto = NewCustomerRequestDto.builder().build();
        customerOrdersRequestDto = CustomerOrdersRequestDto.builder().build();
        newCustomerResponseDto = NewCustomerResponseDto.builder().build();
        customerOrdersResponseDto = CustomerOrdersResponseDto.builder().build();
    }

    @Test
    void testNewCustomer() {
        Mockito.when(customerService.newCustomer(any(NewCustomerRequestDto.class))).thenReturn(newCustomerResponseDto);
        ResponseEntity<NewCustomerResponseDto> newCustomerResponseDtoResponseEntity = customerController.save(newCustomerRequestDto);
        Assertions.assertNotNull(newCustomerResponseDtoResponseEntity);
    }

    @Test
    void testOrders() {
        Mockito.when(orderService.orders(any(CustomerOrdersRequestDto.class))).thenReturn(customerOrdersResponseDto);
        ResponseEntity<CustomerOrdersResponseDto> ordersRequestDtoResponseEntity = customerController.orders(customerOrdersRequestDto);
        Assertions.assertNotNull(ordersRequestDtoResponseEntity);
    }


}