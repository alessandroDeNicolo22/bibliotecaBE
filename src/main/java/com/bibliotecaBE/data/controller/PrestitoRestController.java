package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.PrestitoAPI;
import com.bibliotecaBE.data.dto.Request.PrestitoRequest;
import com.bibliotecaBE.data.dto.Response.PrestitoResponse;
import com.bibliotecaBE.data.service.PrestitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class PrestitoRestController implements PrestitoAPI {

    @Autowired
    PrestitoService service;

    @Override
    public ResponseEntity<ArrayList<PrestitoResponse>> getAllPrestiti(HttpServletRequest request){
        return new ResponseEntity<>(this.service.getAllPrestiti(), HttpStatus.OK);
    }


    @Override
    public Page<PrestitoResponse> page(HttpServletRequest request, Integer id, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<PrestitoResponse> findById(HttpServletRequest request, Integer id) {
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> save(HttpServletRequest request, PrestitoRequest oPreventivo) {
        this.service.save(oPreventivo);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> delete(HttpServletRequest request, Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
