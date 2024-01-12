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

import com.bibliotecaBE.data.dto.Request.CasaeditriceRequest;
import com.bibliotecaBE.data.dto.Response.CasaeditriceResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/ce")

public interface CasaeditriceAPI {

	@GetMapping(path = "/list", produces = "application/json")
    ResponseEntity<ArrayList<CasaeditriceResponse>> getAllCE(HttpServletRequest request);

	@GetMapping(path = "/listpage", produces = "application/json")
    Page<CasaeditriceResponse> getAllCEPage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

	@GetMapping(path = "/findbyid/{id}", produces = "application/json")
    ResponseEntity<CasaeditriceResponse> findById(HttpServletRequest request, @PathVariable Integer id);

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    ResponseEntity<?> insertUpdate(HttpServletRequest request, @RequestBody CasaeditriceRequest oCasaeditriceRequest);

	@GetMapping(path = "/checkDelete/{id}", produces = "application/json")
    ResponseEntity<Boolean> checkDelete(HttpServletRequest request, @PathVariable Integer id);

	@DeleteMapping(path = "/delete/{id}", produces = "application/json")
    ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Integer id);

}
