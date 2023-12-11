package com.bibliotecaBE.data.controller.api;

import com.bibliotecaBE.data.dto.Request.OrdinedettaglioRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RequestMapping(path = "/ordinedettaglio")
@CrossOrigin(origins = "http://localhost:4200")
public interface OrdinedettaglioAPI {
    @PostMapping(path = "/saveDettagli", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> saveDettagli (HttpServletRequest request, @RequestBody ArrayList<OrdinedettaglioRequest> elencoDettagli);
}
