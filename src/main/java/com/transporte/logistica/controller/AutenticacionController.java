package com.transporte.logistica.controller;

import com.transporte.logistica.config.AuthRequest;
import com.transporte.logistica.config.AuthResponse;
import com.transporte.logistica.config.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.transporte.logistica.config.PBKDF2Encoder;
import com.transporte.logistica.service.UsuarioService;


/**
 *
 * @author persi
 */
@AllArgsConstructor
@RestController
public class AutenticacionController  {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return usuarioService.findByUsername(ar.getUsername())
            .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
            .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}