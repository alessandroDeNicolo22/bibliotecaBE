package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.PreventivoAPI;
import com.bibliotecaBE.data.dto.Request.PreventivoRequest;
import com.bibliotecaBE.data.dto.Response.PreventivoResponse;
import com.bibliotecaBE.data.service.PreventivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class PreventivoRestController implements PreventivoAPI {

    @Autowired
    PreventivoService preventivoService;

    @Override
    public ResponseEntity<ArrayList<PreventivoResponse>> getAllPreventivi(HttpServletRequest request){
        return new ResponseEntity<ArrayList<PreventivoResponse>>(this.preventivoService.getAllPreventivi(), HttpStatus.OK);
    }


    @Override
    public Page<PreventivoResponse> page(HttpServletRequest request, Integer id, Integer pageIndex, Integer pageSize) {
        return this.preventivoService.getPagePerIdFornitore(id, pageIndex, pageSize);
    }

    @Override
    public ResponseEntity<PreventivoResponse> findById(HttpServletRequest request, Integer id) {
        return new ResponseEntity<PreventivoResponse>(this.preventivoService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, PreventivoRequest oPreventivo) {
        this.preventivoService.save(oPreventivo);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> verificaElimina(HttpServletRequest request, Integer id) {
        return new ResponseEntity<Boolean>(this.preventivoService.checkDelete(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> elimina(HttpServletRequest request, Integer id) {
        this.preventivoService.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
