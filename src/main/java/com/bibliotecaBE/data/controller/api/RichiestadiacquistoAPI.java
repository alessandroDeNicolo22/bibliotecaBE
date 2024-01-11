package com.bibliotecaBE.data.controller.api;


import com.bibliotecaBE.data.dto.Request.RichiestaDiAcquistoRequest;
import com.bibliotecaBE.data.dto.Response.RichiestaDiAcquistoResponse;
import com.bibliotecaBE.data.dto.Response.StudenteResponse;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;

@RequestMapping(path="/richiestadiacquisto")
@CrossOrigin(origins = "http://localhost:4200")
public interface RichiestadiacquistoAPI {
    @GetMapping(path = "/list", produces = "application/json")
    ResponseEntity<ArrayList<RichiestaDiAcquistoResponse>> getAllRichieste(HttpServletRequest request);

    @GetMapping(path = "/listpage", produces = "application/json")
    Page<RichiestaDiAcquistoResponse> getAllRichiestePage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findbyid/{id}", produces = "application/json")
    ResponseEntity<RichiestaDiAcquistoResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    ResponseEntity<?> save (HttpServletRequest request, @RequestBody RichiestaDiAcquistoRequest oRichiestaDiAcquistoRequest);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    ResponseEntity<?> delete (HttpServletRequest request, @PathVariable Integer id);

}
