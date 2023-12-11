package com.bibliotecaBE.data.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecaBE.data.controller.api.AliquotaivaAPI;
import com.bibliotecaBE.data.dto.Request.AliquotaivaRequest;
import com.bibliotecaBE.data.dto.Response.AliquotaivaResponse;
import com.bibliotecaBE.data.service.AliquotaService;

@RestController
public class AliquotaivaRestController implements AliquotaivaAPI{
//
//@Autowired
//AliquotaService service;

	private final AliquotaService service;
	public AliquotaivaRestController(AliquotaService service){
		this.service = service;
	}


	@Override
	public ResponseEntity<ArrayList<AliquotaivaResponse>> getAllFornitori(HttpServletRequest request) {
		return ResponseEntity.ok(service.getAllAliquota());
	}

	@Override
	public Page<AliquotaivaResponse> getAllFornitoriPage(HttpServletRequest request, int pageIndex, int pageSize) {
		return service.getPageAliquote(pageIndex, pageSize);
	}

	@Override
	public ResponseEntity<AliquotaivaResponse> findById(HttpServletRequest request, Integer id) {
		return ResponseEntity.ok(service.getAliquotaivaById(id));
	}

	@Override
	public ResponseEntity<?> insertUpdate(HttpServletRequest request, AliquotaivaRequest oAliquotaivaRequest) {
		service.save(oAliquotaivaRequest);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(HttpServletRequest request, Integer id) {
		service.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> check(HttpServletRequest request, Integer id) {
		return new ResponseEntity<Boolean>(service.checkElimina(id),HttpStatus.OK);
	}

}
