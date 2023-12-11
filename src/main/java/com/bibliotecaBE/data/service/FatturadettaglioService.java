package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.FatturadettaglioRequest;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
@Service
public interface FatturadettaglioService {
    public void saveDettagli(ArrayList<FatturadettaglioRequest> elencoDettagli);
}
