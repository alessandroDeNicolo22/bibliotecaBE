package com.europCarBE.data.controller.api;

import com.europCarBE.data.dto.Request.FilterDateRequest;
import com.europCarBE.data.dto.Request.OrdineacquistoRequest;
import com.europCarBE.data.dto.Response.OrdineacquistoResponse;
import com.europCarBE.data.dto.Response.OrdinedettaglioResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

@RequestMapping(path = "/ordineacquisto")
@CrossOrigin(origins = "http://localhost:4200")
public interface OrdineacquistoAPI {
    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<ArrayList<OrdineacquistoResponse>> list(HttpServletRequest request);
    @PostMapping(path = "/listByFornitore", produces = "application/json" , consumes = "application/json")
    public  ResponseEntity<Page<OrdineacquistoResponse>> getOrdiniByFornitore(HttpServletRequest request,@RequestBody Integer id,@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findbyid/{id}", produces = "application/json")
    public ResponseEntity<OrdineacquistoResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> saveOrdine (HttpServletRequest request, @RequestBody OrdineacquistoRequest oOrdineAcquistoRequest);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/findDettagli", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Page<OrdinedettaglioResponse>> findDettagli(HttpServletRequest request, @RequestBody Integer id,@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findDettagli1", produces = "application/json")
    public ResponseEntity<ArrayList<OrdinedettaglioResponse>> findDettagli1(HttpServletRequest request, @RequestParam int id);

    @PostMapping(path = "/filterDate", produces = "application/json", consumes = "application/json")
    public Page<OrdineacquistoResponse> filterDate (HttpServletRequest request, @RequestBody FilterDateRequest filterDateRequest, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @PostMapping(path = "/filterOnlyDate", produces = "application/json", consumes = "application/json")
    public Page<OrdineacquistoResponse> filterOnlyDate (HttpServletRequest request, @RequestBody FilterDateRequest filterDateRequest, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

}

