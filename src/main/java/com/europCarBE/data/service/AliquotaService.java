package com.europCarBE.data.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.europCarBE.data.dto.Request.AliquotaivaRequest;
import com.europCarBE.data.dto.Response.AliquotaivaResponse;

@Service
public interface AliquotaService {

	public ArrayList<AliquotaivaResponse>getAllAliquota();
	public AliquotaivaResponse getAliquotaivaById(Integer id);
	public void save(AliquotaivaRequest oAliquotaivaRequest);
	public void deleteById(Integer id);
	public Boolean checkElimina(Integer id);
	public Page<AliquotaivaResponse>getPageAliquote(Integer pageIndex, Integer pageSize);
}
