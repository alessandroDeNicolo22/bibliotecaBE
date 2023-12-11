package com.europCarBE.data.controller;

import com.europCarBE.data.controller.api.AuthenticationAPI;
import com.europCarBE.data.dto.Request.AuthenticationRequest;
import com.europCarBE.data.service.AuthenticationService;
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
