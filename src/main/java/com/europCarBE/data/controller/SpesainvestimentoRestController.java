package com.europCarBE.data.controller;

import com.europCarBE.data.controller.api.SpesainvestimentoAPI;
import com.europCarBE.data.dto.Request.SpesainvestimentoRequest;
import com.europCarBE.data.dto.Response.SpesainvestimentoResponse;
import com.europCarBE.data.service.SpesainvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
@RestController
public class SpesainvestimentoRestController implements SpesainvestimentoAPI {
    @Autowired
    SpesainvestimentoService service;

    @Override
    public ResponseEntity<ArrayList<SpesainvestimentoResponse>> getAllSpeseInvestimento(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAllSpesaInvestimento());
    }

    @Override
    public Page<SpesainvestimentoResponse> getAllSpeseInvestimentoPage(HttpServletRequest request, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<SpesainvestimentoResponse> findbyid(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, SpesainvestimentoRequest oSpesaInvestimentoRequest) {
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
    public Page<SpesainvestimentoResponse> getSpesaInvestimentoByIdSottoCategoria(HttpServletRequest request, Integer id, int pageIndex, int pageSize) {
        return service.getSpesaInvestimentoByIdSottoCategoria(id,pageIndex,pageSize);
    }
}
