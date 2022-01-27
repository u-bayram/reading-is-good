package com.getir.readingisgood.controller;

import com.getir.readingisgood.configuration.JwtTokenUtil;
import com.getir.readingisgood.dto.LoginRequestDto;
import com.getir.readingisgood.dto.LoginResponseDto;
import com.getir.readingisgood.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

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
    public ResponseEntity<LoginResponseDto> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) throws Exception {
        authenticate(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        final UserDetails userDetails = customerService
                .loadUserByUsername(loginRequestDto.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    private void authenticate(String email, String password) throws Exception {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
