package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.OrdinedettaglioAPI;
import com.bibliotecaBE.data.dto.Request.OrdinedettaglioRequest;
import com.bibliotecaBE.data.service.OrdinedettaglioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Objects;

@RestController
public class OrdinedettaglioRestController implements OrdinedettaglioAPI {

    @Autowired
    OrdinedettaglioService service;
    @Override
    public ResponseEntity<?> saveDettagli(HttpServletRequest request, ArrayList<OrdinedettaglioRequest> elencoDettagli) {
        service.saveDettagli(elencoDettagli);
        return new ResponseEntity<Objects>(HttpStatus.OK
        );
    }
}
