package com.bibliotecaBE.data.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bibliotecaBE.data.dto.Request.AreaRequest;
import com.bibliotecaBE.data.dto.Response.CasaeditriceResponse;

@Service
public interface AreaService {
	
	public ArrayList<CasaeditriceResponse> getAllAree();
	
	public Page<CasaeditriceResponse> getAllAreePage(int pageIndex, int pageSize);
	
	public CasaeditriceResponse getAreaById(Integer id);
	
	public void save(AreaRequest area);
	
	public void deleteById(Integer id);

	public Boolean checkDelete(Integer id);

}
