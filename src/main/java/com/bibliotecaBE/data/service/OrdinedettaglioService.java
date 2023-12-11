package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.OrdinedettaglioRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface OrdinedettaglioService {
public void saveDettagli(ArrayList<OrdinedettaglioRequest> elencoDettagli);
}
