package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.FatturapassivaRequest;
import com.europCarBE.data.dto.Request.FilterDateRequest;
import com.europCarBE.data.dto.Request.OrdineacquistoRequest;
import com.europCarBE.data.dto.Response.FatturadettaglioResponse;
import com.europCarBE.data.dto.Response.FatturapassivaResponse;
import com.europCarBE.data.dto.Response.OrdineacquistoResponse;
import com.europCarBE.data.dto.Response.OrdinedettaglioResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public interface FatturapassivaService {
    public ArrayList<FatturapassivaResponse> getAll();
    public Page<FatturapassivaResponse> getAllFatture(int id, int pageIndex, int pageSize);
    public FatturapassivaResponse getFatturaById(Integer id);
    public void save (FatturapassivaRequest fatturapassivaRequest);
    public void deleteById(Integer id);
    public Page<FatturadettaglioResponse> findDettagli(Integer id, int pageIndex, int pageSize);
    public ArrayList<FatturadettaglioResponse> findDettagli1(Integer id);
    public Page<FatturapassivaResponse> filterFatture(FilterDateRequest filterDateRequest, int pageIndex, int pageSize);
    public Page<FatturapassivaResponse> filterOnlyDate(FilterDateRequest filterDateRequest, int pageIndex, int pageSize);
}
