package com.bibliotecaBE.data.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bibliotecaBE.data.dto.Request.AliquotaivaRequest;
import com.bibliotecaBE.data.dto.Response.AutoreResponse;

@Service
public interface AliquotaService {

	public ArrayList<AutoreResponse>getAllAliquota();
	public AutoreResponse getAliquotaivaById(Integer id);
	public void save(AliquotaivaRequest oAliquotaivaRequest);
	public void deleteById(Integer id);
	public Boolean checkElimina(Integer id);
	public Page<AutoreResponse>getPageAliquote(Integer pageIndex, Integer pageSize);
}
