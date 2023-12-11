package com.bibliotecaBE.data.controller.api;

import com.bibliotecaBE.data.dto.Request.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/auth")
public interface AuthenticationAPI {

    @PostMapping(path="/authenticate", produces="application/json", consumes="application/json")
    public ResponseEntity<?> authenticate (HttpServletRequest request, @RequestBody AuthenticationRequest authenticationRequest) throws Exception;
}
