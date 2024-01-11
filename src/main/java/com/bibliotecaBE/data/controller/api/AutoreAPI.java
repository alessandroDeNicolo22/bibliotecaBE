package com.bibliotecaBE.data.controller.api;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.bibliotecaBE.data.dto.Request.AutoreRequest;
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

import com.bibliotecaBE.data.dto.Response.AutoreResponse;

@RequestMapping(path="/autore")
@CrossOrigin(origins = "http://localhost:4200")
public interface AutoreAPI {

	@GetMapping(path = "/list", produces = "application/json")
    ResponseEntity<ArrayList<AutoreResponse>> getAllAutori(HttpServletRequest request);

	@GetMapping(path = "/listpage", produces = "application/json")
    ResponseEntity<Page<AutoreResponse>> getAllAutoriPage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "5") int pageSize);

	@GetMapping(path = "/findbyid/{id}", produces = "application/json")
    ResponseEntity<AutoreResponse> findById(HttpServletRequest request, @PathVariable Integer id);

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    ResponseEntity<?> insertUpdate(HttpServletRequest request, @RequestBody AutoreRequest oAutoreRequest);

	@DeleteMapping(path = "/delete/{id}", produces = "application/json")
    ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

	@GetMapping(path = "/check/{id}", produces = "application/json")
    ResponseEntity<Boolean> check(HttpServletRequest request, @PathVariable Integer id);
}
