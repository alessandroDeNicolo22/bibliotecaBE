package com.bibliotecaBE.data.controller.api;

import com.bibliotecaBE.data.dto.Request.FatturapassivaRequest;
import com.bibliotecaBE.data.dto.Request.FilterDateRequest;
import com.bibliotecaBE.data.dto.Response.CopiaResponse;
import com.bibliotecaBE.data.dto.Response.GenereResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RequestMapping(path = "/fatturapassiva")
@CrossOrigin(origins = "http://localhost:4200")
public interface FatturapassivaAPI {
    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<ArrayList<GenereResponse>> list(HttpServletRequest request);
    @PostMapping(path = "/listByFornitore", produces = "application/json" , consumes = "application/json")
    public  ResponseEntity<Page<GenereResponse>> getOrdiniByFornitore(HttpServletRequest request, @RequestBody Integer id, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findbyid/{id}", produces = "application/json")
    public ResponseEntity<GenereResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> saveFattura (HttpServletRequest request, @RequestBody FatturapassivaRequest fatturapassivaRequest);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/findDettagli", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Page<CopiaResponse>> findDettagli(HttpServletRequest request, @RequestBody Integer id, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findDettagli1", produces = "application/json")
    public ResponseEntity<ArrayList<CopiaResponse>> findDettagli1(HttpServletRequest request, @RequestParam int id);

    @PostMapping(path = "/filterDate", produces = "application/json", consumes = "application/json")
    public Page<GenereResponse> filterDate (HttpServletRequest request, @RequestBody FilterDateRequest filterDateRequest, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @PostMapping(path = "/filterOnlyDate", produces = "application/json", consumes = "application/json")
    public Page<GenereResponse> filterOnlyDate (HttpServletRequest request, @RequestBody FilterDateRequest filterDateRequest, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

}
