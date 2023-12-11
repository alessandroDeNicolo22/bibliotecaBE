package com.europCarBE.data.controller;

import com.europCarBE.data.controller.api.FatturadettaglioAPI;
import com.europCarBE.data.dto.Request.FatturadettaglioRequest;
import com.europCarBE.data.service.FatturadettaglioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
@RestController
public class FatturadettaglioRestController implements FatturadettaglioAPI {

    @Autowired
    FatturadettaglioService service;

    @Override
    public ResponseEntity<?> saveDettagli(HttpServletRequest request, ArrayList<FatturadettaglioRequest> elencoDettagli) {
        service.saveDettagli(elencoDettagli);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
