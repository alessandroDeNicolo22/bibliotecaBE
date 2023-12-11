package com.europCarBE.data.controller;

import com.europCarBE.data.controller.api.FatturapassivaAPI;
import com.europCarBE.data.dto.Request.FatturapassivaRequest;
import com.europCarBE.data.dto.Request.FilterDateRequest;
import com.europCarBE.data.dto.Request.OrdineacquistoRequest;
import com.europCarBE.data.dto.Response.FatturadettaglioResponse;
import com.europCarBE.data.dto.Response.FatturapassivaResponse;
import com.europCarBE.data.service.FatturapassivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
@RestController
public class FatturapassivaRestController implements FatturapassivaAPI {
@Autowired
    FatturapassivaService service;
    @Override
    public ResponseEntity<ArrayList<FatturapassivaResponse>> list(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<Page<FatturapassivaResponse>> getOrdiniByFornitore(HttpServletRequest request, Integer id, int pageIndex, int pageSize) {
        return ResponseEntity.ok(service.getAllFatture(id,pageIndex,pageSize));
    }

    @Override
    public ResponseEntity<FatturapassivaResponse> findById(HttpServletRequest request, Integer id) {

        return ResponseEntity.ok(service.getFatturaById(id));
    }

    @Override
    public ResponseEntity<?> saveFattura(HttpServletRequest request, FatturapassivaRequest fatturapassivaRequest) {
        service.save(fatturapassivaRequest);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
        service.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<FatturadettaglioResponse>> findDettagli(HttpServletRequest request, Integer id, int pageIndex, int pageSize) {
        return ResponseEntity.ok(service.findDettagli(id, pageIndex, pageSize));
    }

    @Override
    public ResponseEntity<ArrayList<FatturadettaglioResponse>> findDettagli1(HttpServletRequest request, int id) {
        return ResponseEntity.ok(service.findDettagli1(id));
    }

    @Override
    public Page<FatturapassivaResponse> filterDate(HttpServletRequest request, FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        return service.filterFatture(filterDateRequest, pageIndex, pageSize);
    }

    @Override
    public Page<FatturapassivaResponse> filterOnlyDate(HttpServletRequest request, FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        return service.filterOnlyDate(filterDateRequest, pageIndex, pageSize);
    }
}
