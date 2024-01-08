package com.bibliotecaBE.data.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bibliotecaBE.data.dto.Request.CasaeditriceRequest;
import com.bibliotecaBE.data.dto.Response.CasaeditriceResponse;

@Service
public interface CasaEditriceService {
	
	public ArrayList<CasaeditriceResponse> getAllCaseEditrici();
	
//	public Page<CasaeditriceResponse> getAllCaseEditriciPage(int pageIndex, int pageSize);
	
	public CasaeditriceResponse getCasaEditriceById(Integer id);
	
	public void save(CasaeditriceRequest casaeditriceRequest);
	
	public void deleteById(Integer id);

//	public Boolean checkDelete(Integer id);

}
