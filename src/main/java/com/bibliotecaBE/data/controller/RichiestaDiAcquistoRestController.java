package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.RichiestadiacquistoAPI;
import com.bibliotecaBE.data.dto.Request.RichiestaDiAcquistoRequest;
import com.bibliotecaBE.data.dto.Response.RichiestaDiAcquistoResponse;
import com.bibliotecaBE.data.service.RichiestaDiAcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

@RestController
public class RichiestaDiAcquistoRestController implements RichiestadiacquistoAPI {

    @Autowired
    RichiestaDiAcquistoService service;

    @Override
    public ResponseEntity<ArrayList<RichiestaDiAcquistoResponse>> getAllRichieste(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAllRichiesteDiAcquisto());
    }

    @Override
    public Page<RichiestaDiAcquistoResponse> getAllRichiestePage(HttpServletRequest request, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<RichiestaDiAcquistoResponse> findById(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(service.getRichiestaDiAcquistoById(id));
    }

    @Override
    public ResponseEntity<?> save(HttpServletRequest request, RichiestaDiAcquistoRequest oRichiestaDiAcquistoRequest) {
        service.save(oRichiestaDiAcquistoRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request, Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
