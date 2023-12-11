package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest) throws Exception;
}
