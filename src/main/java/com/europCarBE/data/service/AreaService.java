package com.europCarBE.data.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.europCarBE.data.dto.Request.AreaRequest;
import com.europCarBE.data.dto.Response.AreaResponse;

@Service
public interface AreaService {
	
	public ArrayList<AreaResponse> getAllAree();
	
	public Page<AreaResponse> getAllAreePage(int pageIndex, int pageSize);
	
	public AreaResponse getAreaById(Integer id);
	
	public void save(AreaRequest area);
	
	public void deleteById(Integer id);

	public Boolean checkDelete(Integer id);

}
