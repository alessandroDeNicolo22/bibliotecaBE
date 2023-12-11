package com.bibliotecaBE.data.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.bibliotecaBE.data.dto.Response.AutoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecaBE.data.controller.api.AutoreAPI;
import com.bibliotecaBE.data.dto.Request.AliquotaivaRequest;

@RestController
public class AutoreRestController implements AutoreAPI {


	@Override
	public ResponseEntity<ArrayList<AutoreResponse>> getAllFornitori(HttpServletRequest request) {
		return ResponseEntity.ok(service.getAllAliquota());
	}

	@Override
	public Page<AutoreResponse> getAllFornitoriPage(HttpServletRequest request, int pageIndex, int pageSize) {
		return service.getPageAliquote(pageIndex, pageSize);
	}

	@Override
	public ResponseEntity<AutoreResponse> findById(HttpServletRequest request, Integer id) {
		return ResponseEntity.ok(service.getAliquotaivaById(id));
	}

	@Override
	public ResponseEntity<?> insertUpdate(HttpServletRequest request, AliquotaivaRequest oAliquotaivaRequest) {
		service.save(oAliquotaivaRequest);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
		service.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> check(HttpServletRequest request, Integer id) {
		return new ResponseEntity<Boolean>(service.checkElimina(id),HttpStatus.OK);
	}

}
