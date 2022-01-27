package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.CustomerOrdersRequestDto;
import com.getir.readingisgood.dto.CustomerOrdersResponseDto;
import com.getir.readingisgood.dto.NewCustomerRequestDto;
import com.getir.readingisgood.dto.NewCustomerResponseDto;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.service.OrderService;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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
    public ResponseEntity<NewCustomerResponseDto> newCustomer(@RequestBody @Validated NewCustomerRequestDto newCustomerRequestDto) {
        log.info("newCustomer request.");
        return new ResponseEntity<NewCustomerResponseDto>(customerService.newCustomer(newCustomerRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/orders")
    public ResponseEntity<CustomerOrdersResponseDto> orders(@RequestBody @Validated CustomerOrdersRequestDto customerOrdersRequestDto) {
        log.info("customer orders request.");
        return new ResponseEntity<CustomerOrdersResponseDto>(orderService.orders(customerOrdersRequestDto), HttpStatus.CREATED);
    }


}
