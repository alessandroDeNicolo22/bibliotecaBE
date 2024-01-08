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
    ProfessoreService progettoService;

	@Override
	public Page<ProfessoreResponse> getAllProgettiPage(HttpServletRequest request, int pageIndex, int pageSize) {
		return progettoService.getAllProgettiPage(pageIndex, pageSize);
	}
	
	@Override
	public ResponseEntity<ArrayList<ProfessoreResponse>> getAllProgetti(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<ProfessoreResponse>>(progettoService.getAllProgetti(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProfessoreResponse> findById(HttpServletRequest request, Integer id) {
		return new ResponseEntity<ProfessoreResponse>(progettoService.findById(id), HttpStatus.OK);
	}

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, ProfessoreRequest oProfessoreRequest) {
        progettoService.save(oProfessoreRequest);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
        progettoService.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> checkDelete(HttpServletRequest request, Integer id) {
        return new ResponseEntity<Boolean>(progettoService.check(id), HttpStatus.OK);
    }


}
