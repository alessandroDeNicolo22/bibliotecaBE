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

import com.bibliotecaBE.data.dto.Request.AliquotaivaRequest;
import com.bibliotecaBE.data.dto.Response.AliquotaivaResponse;

@RequestMapping(path="/aliquotaiva")
@CrossOrigin(origins = "http://localhost:4200")
public interface AliquotaivaAPI {

	@GetMapping(path = "/list", produces = "application/json")
	public ResponseEntity<ArrayList<AliquotaivaResponse>> getAllFornitori(HttpServletRequest request);

	@GetMapping(path = "/listpage", produces = "application/json")
	public Page<AliquotaivaResponse> getAllFornitoriPage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

	@GetMapping(path = "/findbyid/{id}", produces = "application/json")
	public ResponseEntity<AliquotaivaResponse> findById(HttpServletRequest request, @PathVariable Integer id);

	@PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> insertUpdate(HttpServletRequest request, @RequestBody AliquotaivaRequest oAliquotaivaRequest);

	@DeleteMapping(path = "/delete/{id}", produces = "application/json")
	public ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

	@GetMapping(path = "/check/{id}", produces = "application/json")
	public ResponseEntity<Boolean> check(HttpServletRequest request, @PathVariable Integer id);
}
