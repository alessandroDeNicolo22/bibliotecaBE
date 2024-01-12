package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.GenereRequest;
import com.bibliotecaBE.data.dto.Response.AutoreResponse;
import com.bibliotecaBE.data.dto.Response.GenereResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public interface GenereService {
    public ArrayList<GenereResponse> getAll();
//    public Page<GenereResponse> getAllFatture(int id, int pageIndex, int pageSize);

    public GenereResponse getGenereById(Integer id);

    public void save (GenereRequest genereRequest);

    public void deleteById(Integer id);

    public Boolean checkElimina(Integer id);

    public Page<GenereResponse>getPageGeneri(Integer pageIndex, Integer pageSize);
}
