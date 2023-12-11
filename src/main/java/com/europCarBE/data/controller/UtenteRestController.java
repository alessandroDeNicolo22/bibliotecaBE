package com.europCarBE.data.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.europCarBE.data.controller.api.UtenteAPI;
import com.europCarBE.data.dto.Request.UtenteRequest;
import com.europCarBE.data.dto.Response.UtenteResponse;
import com.europCarBE.data.enums.Role;
import com.europCarBE.data.service.UtenteService;

@RestController

public class UtenteRestController implements UtenteAPI {

    @Autowired
    UtenteService utenteService;

    @Override
    public ResponseEntity<ArrayList<UtenteResponse>> getAllUtenti(HttpServletRequest request) {
        return new ResponseEntity<ArrayList<UtenteResponse>>(utenteService.getAllUtenti(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UtenteResponse> findById(HttpServletRequest request, Integer id) {
        return new ResponseEntity<UtenteResponse>(utenteService.getUtenteById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, UtenteRequest oUtenteRequest) {
        utenteService.saveUser(oUtenteRequest);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addRoleToUser(HttpServletRequest request, String email, Role role) {
        utenteService.addRoleToUser(email, role);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
        utenteService.deleteById(id);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UtenteResponse> findByEmail(HttpServletRequest request, String email) {
        return new ResponseEntity<UtenteResponse>(utenteService.getUtenteByUsername(email), HttpStatus.OK);
    }

    @Override
    public Page<UtenteResponse> getAllUtentiPaginated(HttpServletRequest request, Integer pageIndex, Integer pageSize) {
        return utenteService.getPaginatedUtenti(pageIndex, pageSize);
    }

    @Override
    public ResponseEntity<Boolean> checkEmail(HttpServletRequest request, UtenteRequest oUtenteRequest) {
        return new ResponseEntity<Boolean>(this.utenteService.emailEsistente(oUtenteRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> verifyPassword(HttpServletRequest request, Integer id, String password) {
        return new ResponseEntity<Boolean>(this.utenteService.verifyPassword(id, password), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> modifyPassword(HttpServletRequest request, Integer id,String password) {
        utenteService.modifyPassword(id, password);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
