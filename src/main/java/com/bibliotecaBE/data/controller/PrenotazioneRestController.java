package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.PrenotazioneAPI;
import com.bibliotecaBE.data.dto.Request.PrenotazioneRequest;
import com.bibliotecaBE.data.dto.Response.PrenotazioneResponse;
import com.bibliotecaBE.data.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class PrenotazioneRestController implements PrenotazioneAPI {

    @Autowired
    PrenotazioneService service;

    @Override
    public ResponseEntity<List<PrenotazioneResponse>> list(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public Page<PrenotazioneResponse> getAllPrenotazioniPage(HttpServletRequest request, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<PrenotazioneResponse> findById(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(service.getPrenotazioneById(id));
    }

    @Override
    public ResponseEntity<?> save(HttpServletRequest request, PrenotazioneRequest prenotazioneRequest) {
        service.save(prenotazioneRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
