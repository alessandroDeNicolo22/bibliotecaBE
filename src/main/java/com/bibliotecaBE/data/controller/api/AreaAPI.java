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

import com.bibliotecaBE.data.dto.Request.AreaRequest;
import com.bibliotecaBE.data.dto.Response.CasaeditriceResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/area")

public interface AreaAPI {

	@GetMapping(path = "/list", produces = "application/json")
	public ResponseEntity<ArrayList<CasaeditriceResponse>> getAllAree(HttpServletRequest request);

	@GetMapping(path = "/listpage", produces = "application/json")
	public Page<CasaeditriceResponse> getAllAreePage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

	@GetMapping(path = "/findbyid/{id}", produces = "application/json")
	public ResponseEntity<CasaeditriceResponse> findById(HttpServletRequest request, @PathVariable Integer id);

	@PostMapping(path = "/nuovoModifica", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> insertUpdate(HttpServletRequest request, @RequestBody AreaRequest oAreaRequest);

	@GetMapping(path = "/checkElimina/{id}", produces = "application/json")
	public ResponseEntity<Boolean> checkDelete(HttpServletRequest request, @PathVariable Integer id);

	@DeleteMapping(path = "/elimina/{id}", produces = "application/json")
	public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Integer id);

}
