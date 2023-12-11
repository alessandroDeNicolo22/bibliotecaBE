package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.FilterDateRequest;
import com.bibliotecaBE.data.dto.Request.OrdineacquistoRequest;
import com.bibliotecaBE.data.dto.Response.LibroResponse;
import com.bibliotecaBE.data.dto.Response.OrdinedettaglioResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface OrdineacquistoService {
    public ArrayList<LibroResponse> getAll();
    public Page<LibroResponse> getAllOrdini(int id, int pageIndex, int pageSize);
    public LibroResponse getOrdineById(Integer id);
    public void save (OrdineacquistoRequest ordineacquistoRequest);
    public void deleteById(Integer id);
    public Page<OrdinedettaglioResponse> findDettagli(Integer id, int pageIndex,int pageSize);
    public ArrayList<OrdinedettaglioResponse> findDettagli1(Integer id);
    public Page<LibroResponse> filterOrdini(FilterDateRequest filterDateRequest, int pageIndex, int pageSize);
    public Page<LibroResponse> filterOnlyDate(FilterDateRequest filterDateRequest, int pageIndex, int pageSize);
}
