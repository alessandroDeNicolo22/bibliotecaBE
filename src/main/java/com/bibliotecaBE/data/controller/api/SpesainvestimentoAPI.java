package com.bibliotecaBE.data.controller.api;

import com.bibliotecaBE.data.dto.Request.SpesainvestimentoRequest;
import com.bibliotecaBE.data.dto.Response.SpesainvestimentoResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
@RequestMapping(path = "/spesainvestimento")
@CrossOrigin(origins = "http://localhost:4200")

public interface SpesainvestimentoAPI {

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<ArrayList<SpesainvestimentoResponse>> getAllSpeseInvestimento(HttpServletRequest request);

    @GetMapping(path = "/listPage", produces = "application/json")
    public Page<SpesainvestimentoResponse> getAllSpeseInvestimentoPage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path ="/findbyid/{id}", produces = "application/json")
    public ResponseEntity<SpesainvestimentoResponse> findbyid(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, @RequestBody SpesainvestimentoRequest oSpesaInvestimentoRequest);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

    @GetMapping(path = "/checkDelete/{id}", produces = "application/json")
    public ResponseEntity<Boolean> checkDelete(HttpServletRequest request, @PathVariable Integer id);

    @GetMapping(path = "/getPageBySottocategoria/{id}", produces = "application/json")
    public Page<SpesainvestimentoResponse> getSpesaInvestimentoByIdSottoCategoria(HttpServletRequest request, @PathVariable Integer id, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);
}
