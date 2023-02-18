package com.compras.compras.security.rest;

import com.compras.compras.security.models.AuthRequest;
import com.compras.compras.security.models.AuthResponse;
import com.compras.compras.security.repository.UserRepository;
import com.compras.compras.security.securityHandle.JWTUtil;
import com.compras.compras.security.securityHandle.PBKDF2Encoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@AllArgsConstructor
@RestController
public class AuthenticationREST {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/resource/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok("Hello admin");
    }

    @GetMapping("/resource/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> user() {
        return ResponseEntity.ok("Hello user");
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userRepository.findByUsername(ar.getUserName())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}
