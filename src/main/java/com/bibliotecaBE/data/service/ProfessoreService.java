package com.bibliotecaBE.data.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bibliotecaBE.data.dto.Request.ProfessoreRequest;
import com.bibliotecaBE.data.dto.Response.ProfessoreResponse;

@Service
public interface ProfessoreService {
	
//	public Page<ProfessoreResponse> getAllProgettiPage(int pageIndex, int pageSize);
	
	public ArrayList<ProfessoreResponse> getAllProfessori();
	
	public ProfessoreResponse findById(Integer id);
	
	public void save(ProfessoreRequest oProfessoreRequest);
	
	public void deleteById(Integer Id);
	
//	public Boolean check(Integer Id);

}
