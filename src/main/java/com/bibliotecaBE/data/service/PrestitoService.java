package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.PrestitoRequest;

import com.bibliotecaBE.data.dto.Response.PrestitoResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface PrestitoService {

    public ArrayList<PrestitoResponse> getAllPrestiti();

//    public Page<PrestitoResponse> getPagePerIdFornitore(Integer id, Integer pageIndex, Integer pageSize);

    public PrestitoResponse findById(Integer id);

    public void save(PrestitoRequest prestitoRequest);

//    public Boolean checkDelete(Integer id);

    public void deleteById(Integer id);

}
