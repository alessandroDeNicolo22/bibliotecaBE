package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.FatturapassivaRequest;
import com.bibliotecaBE.data.dto.Request.FilterDateRequest;
import com.bibliotecaBE.data.dto.Response.CopiaResponse;
import com.bibliotecaBE.data.dto.Response.GenereResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public interface FatturapassivaService {
    public ArrayList<GenereResponse> getAll();
    public Page<GenereResponse> getAllFatture(int id, int pageIndex, int pageSize);
    public GenereResponse getFatturaById(Integer id);
    public void save (FatturapassivaRequest fatturapassivaRequest);
    public void deleteById(Integer id);
    public Page<CopiaResponse> findDettagli(Integer id, int pageIndex, int pageSize);
    public ArrayList<CopiaResponse> findDettagli1(Integer id);
    public Page<GenereResponse> filterFatture(FilterDateRequest filterDateRequest, int pageIndex, int pageSize);
    public Page<GenereResponse> filterOnlyDate(FilterDateRequest filterDateRequest, int pageIndex, int pageSize);
}
