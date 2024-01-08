package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.RichiestadiacquistoAPI;
import com.bibliotecaBE.data.dto.Request.RichiestaDiAcquistoRequest;
import com.bibliotecaBE.data.dto.Response.StudenteResponse;
import com.bibliotecaBE.data.service.RichiestaDiAcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class RichiestaDiAcquistoRestController implements RichiestadiacquistoAPI {
    @Autowired
    RichiestaDiAcquistoService sottocategoriaService;

    @Override
    public ResponseEntity<ArrayList<StudenteResponse>> getAllSottocategoria(HttpServletRequest request) {
        return ResponseEntity.ok(sottocategoriaService.getAllSottocategoria());
    }

    @Override
    public Page<StudenteResponse> getAllSottocategoriaPage(HttpServletRequest request, int pageIndex, int pageSize) {
        return sottocategoriaService.getAllSottocategoriaPage(pageIndex,pageSize);
    }

    @Override
    public ResponseEntity<StudenteResponse> findById(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(sottocategoriaService.getSottocategoriaById(id));
    }

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, RichiestaDiAcquistoRequest oRichiestaDiAcquistoRequest) {
        sottocategoriaService.save(oRichiestaDiAcquistoRequest);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
        sottocategoriaService.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> check(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(sottocategoriaService.check(id));
    }

    @Override
    public Page<StudenteResponse> getSottocategoriaByAreaPage(HttpServletRequest request, Integer id, int pageIndex, int pageSize) {
        return sottocategoriaService.getSottocategoriaByAreaPage(id,pageIndex,pageSize);
    }

    @Override
    public ResponseEntity<?> riconciliazione(HttpServletRequest request, Date firstDate, Date endDate) {
        sottocategoriaService.setBudget(firstDate,endDate);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
