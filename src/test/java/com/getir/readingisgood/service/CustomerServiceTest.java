package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.NewCustomerRequestDto;
import com.getir.readingisgood.dto.NewCustomerResponseDto;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author UmutBayram
 */
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private NewCustomerRequestDto newCustomerRequestDto;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository, passwordEncoder);
        newCustomerRequestDto = NewCustomerRequestDto
                .builder()
                .email("test@test.com")
                .name("name")
                .surname("surname")
                .build();
        customer = Customer.builder()
                .email("test@test.com")
                .name("name")
                .surname("surname")
                .password("passwordEncoded")
                .build();

    }

    @Test
    void testNewCustomer() {
        when(passwordEncoder.encode(null)).thenReturn("passwordEncoded");
        when(customerRepository.insert(any(Customer.class))).thenReturn(customer);
        NewCustomerResponseDto newCustomerResponseDto = customerService.newCustomer(newCustomerRequestDto);

        assertNotNull(newCustomerResponseDto);
        assertEquals(newCustomerResponseDto.getEmail(), customer.getEmail());
    }

    @Test
    void testLoadUserByUsername() {
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(customer));
        User user = (User) customerService.loadUserByUsername("test@test.com");
        assertNotNull(user);
        assertEquals(user.getUsername(), customer.getEmail());
    }
}