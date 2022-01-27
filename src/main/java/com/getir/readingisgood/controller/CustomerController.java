package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.CustomerOrdersRequestDto;
import com.getir.readingisgood.dto.CustomerOrdersResponseDto;
import com.getir.readingisgood.dto.NewCustomerRequestDto;
import com.getir.readingisgood.dto.NewCustomerResponseDto;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author UmutBayram
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @PostMapping("/newCustomer")
    public ResponseEntity newCustomer(@RequestBody @Validated NewCustomerRequestDto newCustomerRequestDto) {
        return new ResponseEntity<NewCustomerResponseDto>(customerService.newCustomer(newCustomerRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/orders")
    public ResponseEntity orders(@RequestBody @Validated CustomerOrdersRequestDto customerOrdersRequestDto) {
        return new ResponseEntity<CustomerOrdersResponseDto>(orderService.orders(customerOrdersRequestDto), HttpStatus.CREATED);
    }


}
