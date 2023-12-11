package com.europCarBE.data.controller.api;


import com.europCarBE.data.dto.Request.SottocategoriaRequest;
import com.europCarBE.data.dto.Response.SottocategoriaResponse;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;

@RequestMapping(path="/sottocategoria")
@CrossOrigin(origins = "http://localhost:4200")
public interface SottocategoriaAPI {
    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<ArrayList<SottocategoriaResponse>> getAllSottocategoria(HttpServletRequest request);

    @GetMapping(path = "/listpage", produces = "application/json")
    public Page<SottocategoriaResponse> getAllSottocategoriaPage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findbyid/{id}", produces = "application/json")
    public ResponseEntity<SottocategoriaResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, @RequestBody SottocategoriaRequest oSottocategoriaRequest);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

    @GetMapping(path = "/check/{id}", produces = "application/json")
    public ResponseEntity<Boolean> check(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/listbyarea", produces = "application/json", consumes = "application/json")
    public Page<SottocategoriaResponse> getSottocategoriaByAreaPage(HttpServletRequest request,@RequestBody Integer id,@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @PostMapping(path="/riconciliazione", produces = "application/json")
    public ResponseEntity<?> riconciliazione(HttpServletRequest request, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date firstDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate);
}
