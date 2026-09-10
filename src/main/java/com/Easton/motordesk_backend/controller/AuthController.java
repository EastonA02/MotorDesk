package com.Easton.motordesk_backend.controller;

import com.Easton.motordesk_backend.dto.LoginDto;
import com.Easton.motordesk_backend.dto.LoginResponseDto;
import com.Easton.motordesk_backend.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/motordesk/auth")

public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){
        //Build Authentication token
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(), loginDto.getPassword()
        );

        //Authenticate
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //Generate token
        String token = jwtUtil.generateToken(loginDto.getEmail());

        //wrap token in new LoginResponseDto
        LoginResponseDto responseDto = new LoginResponseDto(token);

        //return
        return ResponseEntity.ok(responseDto);
    }
}
