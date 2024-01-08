package com.bibliotecaBE.data.controller.api;

import com.bibliotecaBE.data.dto.Request.CasaeditriceRequest;
import com.bibliotecaBE.data.dto.Request.CopiaRequest;
import com.bibliotecaBE.data.dto.Response.CasaeditriceResponse;
import com.bibliotecaBE.data.dto.Response.CopiaResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RequestMapping(path = "/copia")
@CrossOrigin(origins = "http://localhost:4200")
public interface CopiaAPI {
    @GetMapping(path = "/list", produces = "application/json")
    ResponseEntity<ArrayList<CopiaResponse>> getAllCopie(HttpServletRequest request);

    @GetMapping(path = "/listpage", produces = "application/json")
    Page<CopiaResponse> getAllCopiePage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findbyid/{id}", produces = "application/json")
    ResponseEntity<CopiaResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/nuovoModifica", produces = "application/json", consumes = "application/json")
    ResponseEntity<?> insertUpdate(HttpServletRequest request, @RequestBody CopiaRequest copiaRequest);

    @GetMapping(path = "/checkElimina/{id}", produces = "application/json")
    ResponseEntity<Boolean> checkDelete(HttpServletRequest request, @PathVariable Integer id);

    @DeleteMapping(path = "/elimina/{id}", produces = "application/json")
    ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Integer id);
}
