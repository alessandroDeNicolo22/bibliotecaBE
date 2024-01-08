package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.dto.Request.PrenotazioneRequest;
import com.bibliotecaBE.data.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Objects;

@RestController
public class PrenotazioneRestController implements OrdinedettaglioAPI {

    @Autowired
    PrenotazioneService service;
    @Override
    public ResponseEntity<?> saveDettagli(HttpServletRequest request, ArrayList<PrenotazioneRequest> elencoDettagli) {
        service.saveDettagli(elencoDettagli);
        return new ResponseEntity<Objects>(HttpStatus.OK
        );
    }
}
