package com.bibliotecaBE.data.service;

import java.util.ArrayList;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface FornitoreService {
	
	public Page<FornitoreResponse> getAllFornitoriPage(int page, int itemsPerPage);
	
	public ArrayList<FornitoreResponse> getAllFornitori();
	
	public FornitoreResponse getFornitoreById(Integer id);
	
	public void save(FornitoreRequest oFornitoreRequest);
	
	public void deleteById(Integer id);
	
	public Boolean check(Integer id);
}
