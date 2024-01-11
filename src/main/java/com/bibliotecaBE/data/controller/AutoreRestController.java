package com.bibliotecaBE.data.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.bibliotecaBE.data.dto.Response.AutoreResponse;
import com.bibliotecaBE.data.dto.Response.CasaeditriceResponse;
import com.bibliotecaBE.data.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecaBE.data.controller.api.AutoreAPI;
import com.bibliotecaBE.data.dto.Request.AutoreRequest;

@RestController
public class AutoreRestController implements AutoreAPI {

	@Autowired
	AutoreService service;


	@Override
	public ResponseEntity<ArrayList<AutoreResponse>> getAllAutori(HttpServletRequest request) {
		return new ResponseEntity<>(service.getAllAutori(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<AutoreResponse>> getAllAutoriPage(HttpServletRequest request, int pageIndex, int pageSize) {
		return new ResponseEntity<>(service.getPageAutori(pageIndex,pageSize),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AutoreResponse> findById(HttpServletRequest request, Integer id) {
		return new ResponseEntity<>(service.getAutoreById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> insertUpdate(HttpServletRequest request, AutoreRequest oAutoreRequest) {
		service.save(oAutoreRequest);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
		service.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> check(HttpServletRequest request, Integer id) {
		return new ResponseEntity<>(service.checkElimina(id), HttpStatus.OK);
	}

}
