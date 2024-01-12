package com.bibliotecaBE.data.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecaBE.data.controller.api.CasaeditriceAPI;
import com.bibliotecaBE.data.dto.Request.CasaeditriceRequest;
import com.bibliotecaBE.data.dto.Response.CasaeditriceResponse;
import com.bibliotecaBE.data.service.CasaEditriceService;

@RestController
public class CasaeditriceRestController implements CasaeditriceAPI {
	
	@Autowired
    CasaEditriceService service;

    @Override
    public ResponseEntity<ArrayList<CasaeditriceResponse>> getAllCE(HttpServletRequest request) {
        return new ResponseEntity<>(service.getAllCaseEditrici(), HttpStatus.OK);
    }
    
    @Override
	public Page<CasaeditriceResponse> getAllCEPage(HttpServletRequest request, int pageIndex, int pageSize) {
		return service.getAllCEPage(pageIndex,pageSize);
	}

    @Override
    public ResponseEntity<CasaeditriceResponse> findById(HttpServletRequest request, Integer id) {
        return new ResponseEntity<>(service.getCasaEditriceById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, CasaeditriceRequest oCasaeditriceRequest) {
        service.save(oCasaeditriceRequest);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> checkDelete(HttpServletRequest request, Integer id) {
        return new ResponseEntity<>(service.checkDelete(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request, Integer id) {
        service.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
