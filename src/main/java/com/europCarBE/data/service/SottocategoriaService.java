package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.FornitoreRequest;
import com.europCarBE.data.dto.Request.SottocategoriaRequest;
import com.europCarBE.data.dto.Response.FornitoreResponse;
import com.europCarBE.data.dto.Response.SottocategoriaResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;

@Service
public interface SottocategoriaService {
    public Page<SottocategoriaResponse> getAllSottocategoriaPage(int page, int itemsPerPage);

    public ArrayList<SottocategoriaResponse> getAllSottocategoria();

    public SottocategoriaResponse getSottocategoriaById(Integer id);

    public void save(SottocategoriaRequest oSottocategoriaRequest);

    public void deleteById(Integer id);

    public Boolean check(Integer id);

    public Page<SottocategoriaResponse> getSottocategoriaByAreaPage(int id,int pageIndex, int pageSize) ;

   public void setBudget(Date firstDate, Date endDate);
}
