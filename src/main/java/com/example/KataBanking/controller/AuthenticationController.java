package com.example.KataBanking.controller;

import com.example.KataBanking.configuration.JwtUtils;
import com.example.KataBanking.model.dto.AuthenticationRequest;
import com.example.KataBanking.model.dto.JwtResponse;
import com.example.KataBanking.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final MyUserDetailsService myUserDetailsService;


    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final UserDetails user = myUserDetailsService.loadUserByUsername(request.getEmail());
        if (user != null){
            String token = jwtUtils.generateToken(user);
            return ResponseEntity.ok(new JwtResponse(token));
        }

        return ResponseEntity.status(400).body(new JwtResponse());
    }
}
