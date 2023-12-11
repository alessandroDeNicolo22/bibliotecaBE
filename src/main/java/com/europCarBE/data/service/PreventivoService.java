package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.PreventivoRequest;
import com.europCarBE.data.dto.Response.AliquotaivaResponse;

import com.europCarBE.data.dto.Response.PreventivoResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface PreventivoService {

    public ArrayList<PreventivoResponse> getAllPreventivi();

    public Page<PreventivoResponse> getPagePerIdFornitore(Integer id, Integer pageIndex, Integer pageSize);

    public PreventivoResponse findById(Integer id);

    public void save(PreventivoRequest oPreventivo);

    public Boolean checkDelete(Integer id);

    public void deleteById(Integer id);

}
