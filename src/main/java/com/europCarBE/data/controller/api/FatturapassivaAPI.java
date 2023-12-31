package com.europCarBE.data.controller.api;

import com.europCarBE.data.dto.Request.FatturapassivaRequest;
import com.europCarBE.data.dto.Request.FilterDateRequest;
import com.europCarBE.data.dto.Request.OrdineacquistoRequest;
import com.europCarBE.data.dto.Response.FatturadettaglioResponse;
import com.europCarBE.data.dto.Response.FatturapassivaResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RequestMapping(path = "/fatturapassiva")
@CrossOrigin(origins = "http://localhost:4200")
public interface FatturapassivaAPI {
    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<ArrayList<FatturapassivaResponse>> list(HttpServletRequest request);
    @PostMapping(path = "/listByFornitore", produces = "application/json" , consumes = "application/json")
    public  ResponseEntity<Page<FatturapassivaResponse>> getOrdiniByFornitore(HttpServletRequest request, @RequestBody Integer id, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findbyid/{id}", produces = "application/json")
    public ResponseEntity<FatturapassivaResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> saveFattura (HttpServletRequest request, @RequestBody FatturapassivaRequest fatturapassivaRequest);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/findDettagli", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Page<FatturadettaglioResponse>> findDettagli(HttpServletRequest request, @RequestBody Integer id, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findDettagli1", produces = "application/json")
    public ResponseEntity<ArrayList<FatturadettaglioResponse>> findDettagli1(HttpServletRequest request, @RequestParam int id);

    @PostMapping(path = "/filterDate", produces = "application/json", consumes = "application/json")
    public Page<FatturapassivaResponse> filterDate (HttpServletRequest request, @RequestBody FilterDateRequest filterDateRequest, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @PostMapping(path = "/filterOnlyDate", produces = "application/json", consumes = "application/json")
    public Page<FatturapassivaResponse> filterOnlyDate (HttpServletRequest request, @RequestBody FilterDateRequest filterDateRequest, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

}
