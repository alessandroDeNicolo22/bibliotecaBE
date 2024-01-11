package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.GenereAPI;
import com.bibliotecaBE.data.dto.Request.GenereRequest;
import com.bibliotecaBE.data.dto.Response.CopiaResponse;
import com.bibliotecaBE.data.dto.Response.GenereResponse;
import com.bibliotecaBE.data.service.GenereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
@RestController
public class GenereRestController implements GenereAPI {

@Autowired
GenereService service;
    @Override
    public ResponseEntity<ArrayList<GenereResponse>> list(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public Page<GenereResponse> getAllGenerePage(HttpServletRequest request, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<GenereResponse> findById(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(service.getGenereById(id));
    }

    @Override
    public ResponseEntity<?> saveGenere(HttpServletRequest request, GenereRequest genereRequest) {
        service.save(genereRequest);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
        service.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
