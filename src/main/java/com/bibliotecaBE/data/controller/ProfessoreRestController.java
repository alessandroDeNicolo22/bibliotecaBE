package com.bibliotecaBE.data.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecaBE.data.controller.api.ProfessoreAPI;
import com.bibliotecaBE.data.dto.Request.ProfessoreRequest;
import com.bibliotecaBE.data.dto.Response.ProfessoreResponse;
import com.bibliotecaBE.data.service.ProfessoreService;

@RestController
public class ProfessoreRestController implements ProfessoreAPI {
	
	@Autowired
    ProfessoreService service;

	@Override
	public Page<ProfessoreResponse> getAllProfessoriPage(HttpServletRequest request, int pageIndex, int pageSize) {
		return null;
	}
	
	@Override
	public ResponseEntity<ArrayList<ProfessoreResponse>> getAllProfessori(HttpServletRequest request) {
		return new ResponseEntity<>(service.getAllProfessori(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProfessoreResponse> findById(HttpServletRequest request, Integer id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}

    @Override
    public ResponseEntity<?> save(HttpServletRequest request, ProfessoreRequest oProfessoreRequest) {
        service.save(oProfessoreRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request, Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
