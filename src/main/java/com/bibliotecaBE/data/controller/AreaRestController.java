package com.bibliotecaBE.data.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecaBE.data.controller.api.AreaAPI;
import com.bibliotecaBE.data.dto.Request.AreaRequest;
import com.bibliotecaBE.data.dto.Response.CasaeditriceResponse;
import com.bibliotecaBE.data.service.AreaService;

@RestController
public class AreaRestController implements AreaAPI {
	
	@Autowired
    AreaService areaService;

    @Override
    public ResponseEntity<ArrayList<CasaeditriceResponse>> getAllAree(HttpServletRequest request) {
        // TODO Auto-generated method stub
        return new ResponseEntity<ArrayList<CasaeditriceResponse>>(areaService.getAllAree(), HttpStatus.OK);
    }
    
    @Override
	public Page<CasaeditriceResponse> getAllAreePage(HttpServletRequest request, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return areaService.getAllAreePage(pageIndex, pageSize);
	}

    @Override
    public ResponseEntity<CasaeditriceResponse> findById(HttpServletRequest request, Integer id) {
        // TODO Auto-generated method stub
        return new ResponseEntity<CasaeditriceResponse>(areaService.getAreaById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, AreaRequest oAreaRequest) {
        // TODO Auto-generated method stub
        areaService.save(oAreaRequest);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> checkDelete(HttpServletRequest request, Integer id) {
        // TODO Auto-generated method stub
        return new ResponseEntity<Boolean>(areaService.checkDelete(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request, Integer id) {
        // TODO Auto-generated method stub
        areaService.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
