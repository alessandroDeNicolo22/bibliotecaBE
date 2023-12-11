package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.AuthenticationAPI;
import com.bibliotecaBE.data.dto.Request.AuthenticationRequest;
import com.bibliotecaBE.data.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationRestController implements AuthenticationAPI {

    @Autowired
    AuthenticationService authenticationService;

    @Override
    public ResponseEntity<?> authenticate(HttpServletRequest request, AuthenticationRequest authenticationRequest) throws Exception {
        return authenticationService.authenticate(authenticationRequest);    }
}
