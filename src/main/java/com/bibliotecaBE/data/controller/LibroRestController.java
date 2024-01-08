package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.PrenotazioneAPI;
import com.bibliotecaBE.data.dto.Request.LibroRequest;
import com.bibliotecaBE.data.dto.Response.LibroResponse;
import com.bibliotecaBE.data.dto.Response.PrenotazioneResponse;
import com.bibliotecaBE.data.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class LibroRestController implements PrenotazioneAPI {
@Autowired
LibroService ordineacquistoService;


    @Override
    public ResponseEntity<ArrayList<LibroResponse>> list(HttpServletRequest request) {
        return ResponseEntity.ok(ordineacquistoService.getAll());
    }

    @Override
    public ResponseEntity<Page<LibroResponse>> getOrdiniByFornitore(HttpServletRequest request, Integer id, int pageIndex, int pageSize) {
        return ResponseEntity.ok(ordineacquistoService.getAllOrdini(id,pageIndex,pageSize));
    }


    @Override
    public ResponseEntity<LibroResponse> findById(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(ordineacquistoService.getOrdineById(id));
    }

    @Override
    public ResponseEntity<?> saveOrdine(HttpServletRequest request, LibroRequest oOrdineAcquistoRequest) {
        ordineacquistoService.save(oOrdineAcquistoRequest);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
        ordineacquistoService.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<PrenotazioneResponse>> findDettagli(HttpServletRequest request, Integer id, int pageIndex, int pageSize) {
        return ResponseEntity.ok(ordineacquistoService.findDettagli(id,pageIndex,pageSize));
    }

    @Override
    public ResponseEntity<ArrayList<PrenotazioneResponse>> findDettagli1(HttpServletRequest request, int id) {
        return ResponseEntity.ok(ordineacquistoService.findDettagli1(id));
    }

    @Override
    public Page<LibroResponse> filterDate(HttpServletRequest request, FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        return ordineacquistoService.filterOrdini(filterDateRequest,pageIndex,pageSize);
    }

    @Override
    public Page<LibroResponse> filterOnlyDate(HttpServletRequest request, FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
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
