package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest) throws Exception;
}
