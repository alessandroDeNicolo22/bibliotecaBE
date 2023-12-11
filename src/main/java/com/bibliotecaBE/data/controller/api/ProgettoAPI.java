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

import com.bibliotecaBE.data.dto.Request.ProgettoRequest;
import com.bibliotecaBE.data.dto.Response.ProgettoResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/progetto")

public interface ProgettoAPI {

	@GetMapping(path = "/list", produces = "application/json")
	public ResponseEntity<ArrayList<ProgettoResponse>> getAllProgetti(HttpServletRequest request);

	@GetMapping(path = "/list-page", produces = "application/json")
	public Page<ProgettoResponse> getAllProgettiPage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);
	//	public ResponseEntity<ArrayList<ProgettoResponse>> getAllProgetti(HttpServletRequest request);

	@GetMapping(path = "/findbyid/{id}", produces = "application/json")
	public ResponseEntity<ProgettoResponse> findById(HttpServletRequest request, @PathVariable Integer id);

	@PostMapping(path = "/nuovoModifica", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> insertUpdate(HttpServletRequest request, @RequestBody ProgettoRequest oProgettoRequest);

	@DeleteMapping(path = "/elimina/{id}", produces = "application/json")
	public ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

	@GetMapping(path = "/checkElimina/{id}", produces = "application/json")
	public ResponseEntity<Boolean> checkDelete(HttpServletRequest request, @PathVariable Integer id);

}
