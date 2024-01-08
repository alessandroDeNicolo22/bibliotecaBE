package com.bibliotecaBE.data.controller.api;

import com.bibliotecaBE.data.dto.Request.LibroRequest;
import com.bibliotecaBE.data.dto.Request.PrenotazioneRequest;
import com.bibliotecaBE.data.dto.Response.GenereResponse;
import com.bibliotecaBE.data.dto.Response.LibroResponse;
import com.bibliotecaBE.data.dto.Response.PrenotazioneResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RequestMapping(path = "/prenotazione")
@CrossOrigin(origins = "http://localhost:4200")
public interface PrenotazioneAPI {

    @GetMapping(path = "/list", produces = "application/json")
    ResponseEntity<ArrayList<PrenotazioneResponse>> list(HttpServletRequest request);

    @GetMapping(path = "/listpage", produces = "application/json")
    Page<PrenotazioneResponse> getAllPrenotazioniPage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findbyid/{id}", produces = "application/json")
    ResponseEntity<PrenotazioneResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    ResponseEntity<?> save(HttpServletRequest request, @RequestBody PrenotazioneRequest prenotazioneRequest);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

    @GetMapping(path = "/checkElimina/{id}", produces = "application/json")
    ResponseEntity<Boolean> checkDelete(HttpServletRequest request, @PathVariable Integer id);
}

