package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.FilterDateRequest;
import com.europCarBE.data.dto.Request.OrdineacquistoRequest;
import com.europCarBE.data.dto.Response.OrdineacquistoResponse;
import com.europCarBE.data.dto.Response.OrdinedettaglioResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface OrdineacquistoService {
    public ArrayList<OrdineacquistoResponse> getAll();
    public Page<OrdineacquistoResponse> getAllOrdini(int id,int pageIndex, int pageSize);
    public OrdineacquistoResponse getOrdineById(Integer id);
    public void save (OrdineacquistoRequest ordineacquistoRequest);
    public void deleteById(Integer id);
    public Page<OrdinedettaglioResponse> findDettagli(Integer id, int pageIndex,int pageSize);
    public ArrayList<OrdinedettaglioResponse> findDettagli1(Integer id);
    public Page<OrdineacquistoResponse> filterOrdini(FilterDateRequest filterDateRequest, int pageIndex, int pageSize);
    public Page<OrdineacquistoResponse> filterOnlyDate(FilterDateRequest filterDateRequest, int pageIndex, int pageSize);
}
