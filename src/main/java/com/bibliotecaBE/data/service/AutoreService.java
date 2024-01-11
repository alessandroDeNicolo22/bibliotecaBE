package com.bibliotecaBE.data.service;

import java.util.ArrayList;

import com.bibliotecaBE.data.dto.Request.AutoreRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bibliotecaBE.data.dto.Response.AutoreResponse;

@Service
public interface AutoreService {

	public ArrayList<AutoreResponse>getAllAutori();
	public AutoreResponse getAutoreById(Integer id);
	public void save(AutoreRequest oAutoreRequest);
	public void deleteById(Integer id);
    public Boolean checkElimina(Integer id);
	public Page<AutoreResponse>getPageAutori(Integer pageIndex, Integer pageSize);
}
