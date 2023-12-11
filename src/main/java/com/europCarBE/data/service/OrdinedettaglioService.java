package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.OrdinedettaglioRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface OrdinedettaglioService {
public void saveDettagli(ArrayList<OrdinedettaglioRequest> elencoDettagli);
}
