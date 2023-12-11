package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.SpesainvestimentoRequest;
import com.bibliotecaBE.data.dto.Response.SpesainvestimentoResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface SpesainvestimentoService {
    public Page<SpesainvestimentoResponse> getAllSpeseinvestimento(int page, int itemsPerPage);
    public ArrayList<SpesainvestimentoResponse> getAllSpesaInvestimento();
    public SpesainvestimentoResponse findById(Integer id);
    public void save(SpesainvestimentoRequest oSpesaInvestimentoRequest);
    public void deleteById(Integer id);
    public Boolean check(Integer id);
    public Page<SpesainvestimentoResponse> getSpesaInvestimentoByIdSottoCategoria(Integer id, Integer pageIndex, Integer pageSize);
}
