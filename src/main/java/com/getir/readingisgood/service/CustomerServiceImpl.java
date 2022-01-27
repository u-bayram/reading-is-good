package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.NewCustomerRequestDto;
import com.getir.readingisgood.dto.NewCustomerResponseDto;
import com.getir.readingisgood.mapper.CustomerMapper;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author UmutBayram
 */
@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    @Value("${jwt.default.password}")
    private String defaultPassword;

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public NewCustomerResponseDto newCustomer(NewCustomerRequestDto newCustomerRequestDto) {
        Customer customer = CustomerMapper.INSTANCE.customerToNewCustomerRequestDto(newCustomerRequestDto);
        customer.setPassword(passwordEncoder.encode(defaultPassword));
        customer = customerRepository.insert(customer);
        log.info("customer created. ");
        return CustomerMapper.INSTANCE.newCustomerResponseDtoToCustomer(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        Customer customer = optionalCustomer.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        log.info("user login : " + customer.getEmail());
        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
    }
}
