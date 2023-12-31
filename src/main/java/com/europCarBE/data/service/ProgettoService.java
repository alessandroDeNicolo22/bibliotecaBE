package com.europCarBE.data.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.europCarBE.data.dto.Request.ProgettoRequest;
import com.europCarBE.data.dto.Response.ProgettoResponse;

@Service
public interface ProgettoService {
	
	public Page<ProgettoResponse> getAllProgettiPage(int pageIndex, int pageSize);
	
	public ArrayList<ProgettoResponse> getAllProgetti();
	
	public ProgettoResponse findById(Integer id);
	
	public void save(ProgettoRequest oProgettoRequest);
	
	public void deleteById(Integer Id);
	
	public Boolean check(Integer Id);

}
