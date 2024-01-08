package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.StudenteRequest;
import com.bibliotecaBE.data.dto.Response.RichiestaDiAcquistoResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface SpesainvestimentoService {
    public Page<RichiestaDiAcquistoResponse> getAllSpeseinvestimento(int page, int itemsPerPage);
    public ArrayList<RichiestaDiAcquistoResponse> getAllSpesaInvestimento();
    public RichiestaDiAcquistoResponse findById(Integer id);
    public void save(StudenteRequest oSpesaInvestimentoRequest);
    public void deleteById(Integer id);
    public Boolean check(Integer id);
    public Page<RichiestaDiAcquistoResponse> getSpesaInvestimentoByIdSottoCategoria(Integer id, Integer pageIndex, Integer pageSize);
}
