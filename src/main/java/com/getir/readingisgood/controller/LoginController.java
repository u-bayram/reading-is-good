package com.getir.readingisgood.controller;

import com.getir.readingisgood.configuration.JwtTokenUtil;
import com.getir.readingisgood.dto.LoginRequestDto;
import com.getir.readingisgood.dto.LoginResponseDto;
import com.getir.readingisgood.service.CustomerServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author UmutBayram
 */
@Log4j2
@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomerServiceImpl customerService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, CustomerServiceImpl customerService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customerService = customerService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseDto> authenticate(@Valid @RequestBody LoginRequestDto loginRequestDto) throws Exception {
        log.info("authenticate request.");
        authenticate(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        final UserDetails userDetails = customerService
                .loadUserByUsername(loginRequestDto.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    private void authenticate(String email, String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

}
