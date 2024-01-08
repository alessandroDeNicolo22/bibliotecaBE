package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.CopiaAPI;
import com.bibliotecaBE.data.dto.Request.CopiaRequest;
import com.bibliotecaBE.data.service.CopiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
@RestController
public class CopiaRestController implements CopiaAPI {

    @Autowired
    CopiaService service;

    @Override
    public ResponseEntity<?> saveDettagli(HttpServletRequest request, ArrayList<CopiaRequest> elencoDettagli) {
        service.saveDettagli(elencoDettagli);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
