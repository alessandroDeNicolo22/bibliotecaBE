package com.europCarBE.data.service;

import com.europCarBE.data.config.ServiceJWT;
import com.europCarBE.data.dto.Request.AuthenticationRequest;
import com.europCarBE.data.dto.Response.AuthenticationResponse;
import com.europCarBE.data.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UtenteRepository utenteRepo;

    @Autowired
    ServiceJWT authService;

    @Autowired
    AuthenticationManager authManager;

    @Override
    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
            var utente = utenteRepo.findByEmail(authenticationRequest.getEmail()).orElseThrow();
            System.out.println(utente);
            var jwtToken = authService.generateToken(utente);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setToken(jwtToken);
            return new ResponseEntity<Object>(authenticationResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<String>("Credenziali Errate", HttpStatus.UNAUTHORIZED);

        } catch (Exception ex) {
            return new ResponseEntity<String>("Errore del Server", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
