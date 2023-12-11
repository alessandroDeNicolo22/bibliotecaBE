package com.europCarBE.data.controller;

import com.europCarBE.data.controller.api.SottocategoriaAPI;
import com.europCarBE.data.dto.Request.SottocategoriaRequest;
import com.europCarBE.data.dto.Response.SottocategoriaResponse;
import com.europCarBE.data.service.SottocategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class SottocategoriaRestController implements SottocategoriaAPI {
    @Autowired
    SottocategoriaService sottocategoriaService;

    @Override
    public ResponseEntity<ArrayList<SottocategoriaResponse>> getAllSottocategoria(HttpServletRequest request) {
        return ResponseEntity.ok(sottocategoriaService.getAllSottocategoria());
    }

    @Override
    public Page<SottocategoriaResponse> getAllSottocategoriaPage(HttpServletRequest request, int pageIndex, int pageSize) {
        return sottocategoriaService.getAllSottocategoriaPage(pageIndex,pageSize);
    }

    @Override
    public ResponseEntity<SottocategoriaResponse> findById(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(sottocategoriaService.getSottocategoriaById(id));
    }

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, SottocategoriaRequest oSottocategoriaRequest) {
        sottocategoriaService.save(oSottocategoriaRequest);
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
    public Page<SottocategoriaResponse> getSottocategoriaByAreaPage(HttpServletRequest request, Integer id, int pageIndex, int pageSize) {
        return sottocategoriaService.getSottocategoriaByAreaPage(id,pageIndex,pageSize);
    }

    @Override
    public ResponseEntity<?> riconciliazione(HttpServletRequest request, Date firstDate, Date endDate) {
        sottocategoriaService.setBudget(firstDate,endDate);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
