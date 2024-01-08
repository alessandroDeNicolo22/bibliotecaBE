package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.StudenteAPI;
import com.bibliotecaBE.data.dto.Request.StudenteRequest;
import com.bibliotecaBE.data.dto.Response.RichiestaDiAcquistoResponse;
import com.bibliotecaBE.data.service.SpesainvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
@RestController
public class StudenteRestController implements StudenteAPI {
    @Autowired
    SpesainvestimentoService service;

    @Override
    public ResponseEntity<ArrayList<RichiestaDiAcquistoResponse>> getAllSpeseInvestimento(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAllSpesaInvestimento());
    }

    @Override
    public Page<RichiestaDiAcquistoResponse> getAllSpeseInvestimentoPage(HttpServletRequest request, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<RichiestaDiAcquistoResponse> findbyid(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, StudenteRequest oSpesaInvestimentoRequest) {
        service.save(oSpesaInvestimentoRequest);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
        service.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> checkDelete(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(service.check(id));
    }

    @Override
    public Page<RichiestaDiAcquistoResponse> getSpesaInvestimentoByIdSottoCategoria(HttpServletRequest request, Integer id, int pageIndex, int pageSize) {
        return service.getSpesaInvestimentoByIdSottoCategoria(id,pageIndex,pageSize);
    }
}
