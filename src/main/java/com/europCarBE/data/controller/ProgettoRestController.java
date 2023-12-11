package com.europCarBE.data.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.europCarBE.data.controller.api.ProgettoAPI;
import com.europCarBE.data.dto.Request.ProgettoRequest;
import com.europCarBE.data.dto.Response.ProgettoResponse;
import com.europCarBE.data.service.ProgettoService;

@RestController
public class ProgettoRestController implements ProgettoAPI {
	
	@Autowired
    ProgettoService progettoService;

	@Override
	public Page<ProgettoResponse> getAllProgettiPage(HttpServletRequest request, int pageIndex, int pageSize) {
		return progettoService.getAllProgettiPage(pageIndex, pageSize);
	}
	
	@Override
	public ResponseEntity<ArrayList<ProgettoResponse>> getAllProgetti(HttpServletRequest request) {
		return new ResponseEntity<ArrayList<ProgettoResponse>>(progettoService.getAllProgetti(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProgettoResponse> findById(HttpServletRequest request, Integer id) {
		return new ResponseEntity<ProgettoResponse>(progettoService.findById(id), HttpStatus.OK);
	}

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, ProgettoRequest oProgettoRequest) {
        progettoService.save(oProgettoRequest);
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
