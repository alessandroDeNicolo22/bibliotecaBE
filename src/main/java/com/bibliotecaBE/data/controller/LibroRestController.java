package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.LibroAPI;
import com.bibliotecaBE.data.controller.api.PrenotazioneAPI;
import com.bibliotecaBE.data.dto.Request.LibroRequest;
import com.bibliotecaBE.data.dto.Response.LibroResponse;
import com.bibliotecaBE.data.dto.Response.PrenotazioneResponse;
import com.bibliotecaBE.data.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class LibroRestController implements LibroAPI {

@Autowired
LibroService service;


    @Override
    public ResponseEntity<ArrayList<LibroResponse>> getAllLibro(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<LibroResponse> getLibroById(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(service.getLibroById(id));
    }

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, LibroRequest libroRequest) {
        service.save(libroRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request, Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public Page<LibroResponse> getAllLibriPage(HttpServletRequest request, int pageIndex, int pageSize) {
        return null;
    }

}
