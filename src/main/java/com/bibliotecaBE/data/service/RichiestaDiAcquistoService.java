package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.RichiestaDiAcquistoRequest;
import com.bibliotecaBE.data.dto.Response.RichiestaDiAcquistoResponse;
import com.bibliotecaBE.data.dto.Response.StudenteResponse;
import com.bibliotecaBE.data.entity.RichiestaDiAcquisto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;

@Service
public interface RichiestaDiAcquistoService {
//    public Page<StudenteResponse> getAllSottocategoriaPage(int page, int itemsPerPage);

    public ArrayList<RichiestaDiAcquistoResponse> getAllRichiesteDiAcquisto();

    public RichiestaDiAcquistoResponse getRichiestaDiAcquistoById(Integer id);

    public void save(RichiestaDiAcquistoRequest oRichiestaDiAcquistoRequest);

    public void deleteById(Integer id);

//    public Boolean check(Integer id);
//
//    public Page<StudenteResponse> getSottocategoriaByAreaPage(int id, int pageIndex, int pageSize) ;
//
//   public void setBudget(Date firstDate, Date endDate);
}
