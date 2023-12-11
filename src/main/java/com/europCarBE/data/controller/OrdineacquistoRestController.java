package com.europCarBE.data.controller;

import com.europCarBE.data.controller.api.OrdineacquistoAPI;
import com.europCarBE.data.dto.Request.FilterDateRequest;
import com.europCarBE.data.dto.Request.OrdineacquistoRequest;
import com.europCarBE.data.dto.Response.OrdineacquistoResponse;
import com.europCarBE.data.dto.Response.OrdinedettaglioResponse;
import com.europCarBE.data.entity.Ordinedettaglio;
import com.europCarBE.data.entity.QOrdineacquisto;
import com.europCarBE.data.entity.QOrdinedettaglio;
import com.europCarBE.data.service.OrdineacquistoService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class OrdineacquistoRestController implements OrdineacquistoAPI {
@Autowired
OrdineacquistoService ordineacquistoService;


    @Override
    public ResponseEntity<ArrayList<OrdineacquistoResponse>> list(HttpServletRequest request) {
        return ResponseEntity.ok(ordineacquistoService.getAll());
    }

    @Override
    public ResponseEntity<Page<OrdineacquistoResponse>> getOrdiniByFornitore(HttpServletRequest request, Integer id, int pageIndex, int pageSize) {
        return ResponseEntity.ok(ordineacquistoService.getAllOrdini(id,pageIndex,pageSize));
    }


    @Override
    public ResponseEntity<OrdineacquistoResponse> findById(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(ordineacquistoService.getOrdineById(id));
    }

    @Override
    public ResponseEntity<?> saveOrdine(HttpServletRequest request, OrdineacquistoRequest oOrdineAcquistoRequest) {
        ordineacquistoService.save(oOrdineAcquistoRequest);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
        ordineacquistoService.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<OrdinedettaglioResponse>> findDettagli(HttpServletRequest request, Integer id,int pageIndex, int pageSize) {
        return ResponseEntity.ok(ordineacquistoService.findDettagli(id,pageIndex,pageSize));
    }

    @Override
    public ResponseEntity<ArrayList<OrdinedettaglioResponse>> findDettagli1(HttpServletRequest request, int id) {
        return ResponseEntity.ok(ordineacquistoService.findDettagli1(id));
    }

    @Override
    public Page<OrdineacquistoResponse> filterDate(HttpServletRequest request, FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        return ordineacquistoService.filterOrdini(filterDateRequest,pageIndex,pageSize);
    }

    @Override
    public Page<OrdineacquistoResponse> filterOnlyDate(HttpServletRequest request, FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        return ordineacquistoService.filterOnlyDate(filterDateRequest,pageIndex,pageSize);
    }
//
//    @Override
//    public Page<OrdineacquistoResponse> filtraProgettoData(HttpServletRequest request, Map<String, Object> requestBody, int page, int itemsPerPage) {
//        return null;
//    }
//
//    @Override
//    public Page<OrdineacquistoResponse> filtraSottocategoriaData(HttpServletRequest request, Map<String, Object> requestBody, int page, int itemsPerPage) {
//        return null;
//    }
//
//    @Override
//    public Page<OrdineacquistoResponse> filtraFornitore(HttpServletRequest request, Integer id, int page, int itemsPerPage) {
//        return null;
//    }
//
//    @Override
//    public Page<OrdineacquistoResponse> filtraProgetto(HttpServletRequest request, Integer id, int page, int itemsPerPage) {
//        return null;
//    }
//
//    @Override
//    public Page<OrdineacquistoResponse> filtraSottocategoria(HttpServletRequest request, Integer id, int page, int itemsPerPage) {
//        return null;
//    }
//
//    @Override
//    public Page<OrdineacquistoResponse> filtraData(HttpServletRequest request, Map<String, Object> requestBody, int page, int itemsPerPage) {
//        return null;
//    }


}
