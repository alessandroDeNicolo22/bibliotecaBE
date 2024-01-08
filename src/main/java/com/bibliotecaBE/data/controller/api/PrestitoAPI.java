package com.bibliotecaBE.data.controller.api;

import com.bibliotecaBE.data.dto.Request.PrestitoRequest;
import com.bibliotecaBE.data.dto.Response.PrestitoResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/prestito")
public interface PrestitoAPI {

    @GetMapping(path = "/list", produces = "application/json")
    ResponseEntity<ArrayList<PrestitoResponse>> getAllPrestiti(HttpServletRequest request);

    @GetMapping(path = "/page/{id}", produces = "application/json")
    Page<PrestitoResponse> page(HttpServletRequest request, @PathVariable Integer id, @RequestParam(defaultValue = "0") Integer pageIndex, @RequestParam(defaultValue = "3") Integer pageSize);

    @GetMapping(path = "/findbyid/{id}", produces = "application/json")
    ResponseEntity<PrestitoResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/nuovoModifica", produces = "application/json", consumes = "application/json")
    ResponseEntity<?> save(HttpServletRequest request, @RequestBody PrestitoRequest oPrestitoRequest);

    @GetMapping(path = "/checkDelete/{id}", produces = "application/json")
    ResponseEntity<Boolean> checkDelete(HttpServletRequest request, @PathVariable Integer id);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Integer id);
}
