package com.bibliotecaBE.data.controller.api;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.bibliotecaBE.data.dto.Request.LibroRequest;
import com.bibliotecaBE.data.dto.Response.LibroResponse;
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

@RequestMapping(path="/libro")
@CrossOrigin(origins = "http://localhost:4200")
public interface LibroAPI {
	
	@GetMapping(path="/list",produces = "application/json")
    ResponseEntity<ArrayList<LibroResponse>> getAllLibro(HttpServletRequest request);

	@GetMapping(path="/findbyid/{id}", produces = "application/json")
	public ResponseEntity<LibroResponse> getLibroById(HttpServletRequest request,@PathVariable Integer id);

	@PostMapping(path="/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> insertUpdate (HttpServletRequest request, @RequestBody LibroRequest libroRequest);

	@DeleteMapping(path="/delete/{id}", produces = "application/json")
	public ResponseEntity<?> delete (HttpServletRequest request, @PathVariable Integer id);
	
	@GetMapping(path = "/listpage", produces = "application/json")
	public Page<LibroResponse> getAllLibriPage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "5") int pageSize);

    @GetMapping(path= "/checkDelete/{id}", produces = "application/json")
	public ResponseEntity<Boolean> checkDelete(HttpServletRequest request, @PathVariable Integer id);
}
