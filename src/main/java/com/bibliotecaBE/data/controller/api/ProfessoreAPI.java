package com.bibliotecaBE.data.controller.api;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bibliotecaBE.data.dto.Request.ProfessoreRequest;
import com.bibliotecaBE.data.dto.Response.ProfessoreResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/professore")

public interface ProfessoreAPI {

	@GetMapping(path = "/list", produces = "application/json")
    ResponseEntity<ArrayList<ProfessoreResponse>> getAllProfessori(HttpServletRequest request);

	@GetMapping(path = "/listpage", produces = "application/json")
    ResponseEntity<Page<ProfessoreResponse>> getAllProfessoriPage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

	@GetMapping(path = "/findbyid/{id}", produces = "application/json")
    ResponseEntity<ProfessoreResponse> findById(HttpServletRequest request, @PathVariable Integer id);

	@PostMapping(path = "/nuovoModifica", produces = "application/json", consumes = "application/json")
    ResponseEntity<?> save (HttpServletRequest request, @RequestBody ProfessoreRequest oProfessoreRequest);

	@DeleteMapping(path = "/delete/{id}", produces = "application/json")
    ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Integer id);

    @GetMapping(path = "/check/{id}", produces = "application/json")
    ResponseEntity<Boolean> check(HttpServletRequest request, @PathVariable Integer id);

}
