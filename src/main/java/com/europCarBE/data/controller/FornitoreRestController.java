package com.europCarBE.data.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.europCarBE.data.controller.api.FornitoreAPI;
import com.europCarBE.data.dto.Request.FornitoreRequest;
import com.europCarBE.data.dto.Response.FornitoreResponse;
import com.europCarBE.data.service.FornitoreService;
@RestController
public class FornitoreRestController implements FornitoreAPI{
	@Autowired
	FornitoreService service;

	@Override
	public ResponseEntity<ArrayList<FornitoreResponse>> getAllFornitori(HttpServletRequest request) {
		return ResponseEntity.ok(service.getAllFornitori());
	}

	@Override
	public ResponseEntity<FornitoreResponse> getFornitoreById(HttpServletRequest request, Integer id) {
		return ResponseEntity.ok(service.getFornitoreById(id));
	}

	@Override
	public ResponseEntity<?> insertUpdate(HttpServletRequest request, FornitoreRequest oFornitoreRequest) {
		service.save(oFornitoreRequest);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> delete(HttpServletRequest request, Integer id) {
		service.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@Override
	public Page<FornitoreResponse> getAllFornitoriPage(HttpServletRequest request, int pageIndex, int pageSize) {
		return service.getAllFornitoriPage(pageIndex, pageSize);
	}

	@Override
	public ResponseEntity<Boolean> check(HttpServletRequest request, Integer id) {
		return new ResponseEntity<Boolean>(service.check(id),HttpStatus.OK);
	}

}
