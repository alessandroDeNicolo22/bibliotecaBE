package com.europCarBE.data.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.europCarBE.data.entity.QSottocategoria;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.europCarBE.data.dto.Request.AreaRequest;
import com.europCarBE.data.dto.Response.AreaResponse;
import com.europCarBE.data.entity.Area;
import com.europCarBE.data.repository.AreaRepo;

@Service
@Transactional
public class AreaServiceImpl implements AreaService{

	@Autowired
	AreaRepo areaRepo;

	@Autowired
	EntityManager emanager;

	@Override
	public ArrayList<AreaResponse> getAllAree() {
		// TODO Auto-generated method stub
		return EntitiesToDTO((ArrayList<Area>) areaRepo.findAll());
	}

	private ArrayList<AreaResponse> EntitiesToDTO(ArrayList<Area> elenco) {
		// TODO Auto-generated method stub
		ArrayList<AreaResponse> elencoResponse = new ArrayList<AreaResponse>();
		for(Area oArea: elenco) {
			elencoResponse.add(new AreaResponse(oArea.getId(), oArea.getCodice(), oArea.getArea()));
		}
		return elencoResponse;
	}

	@Override
	public Page<AreaResponse> getAllAreePage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
		Page<Area> areaPage = areaRepo.findAll(pageRequest);
		return areaPage.map(this::entityToDTO);
	}

	public AreaResponse entityToDTO(Area oArea) {
		return new AreaResponse(oArea.getId(), oArea.getCodice(), oArea.getArea());
	}

	@Override
	public AreaResponse getAreaById(Integer id) {
		// TODO Auto-generated method stub
		Area oArea = areaRepo.findById(id).get();
		return new AreaResponse(oArea.getId(), oArea.getCodice(), oArea.getArea());
	}

	@Override
	public void save(AreaRequest area) {
		// TODO Auto-generated method stub
		if (area.getId() == null) {
			areaRepo.save(new Area(area.getId(), area.getCodice(), area.getArea()));
		} else {
			areaRepo.save(this.dTOUpdateEntity(area));
		}
	}

	private Area dTOUpdateEntity(AreaRequest area) {
		// TODO Auto-generated method stub
		Area oArea = areaRepo.findById(area.getId()).get();

		if (area.getCodice() != null) {
			oArea.setCodice(area.getCodice());
		}

		if (area.getArea() != null) {
			oArea.setArea(area.getArea());
		}

		return oArea;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		areaRepo.deleteById(id);

	}

	@Override
	public Boolean checkDelete(Integer id) {
		QSottocategoria oSottocategoria = QSottocategoria.sottocategoria1;
		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
		long nAree = queryFactory.selectFrom(oSottocategoria).where(oSottocategoria.oArea.id.eq(id)).fetch().size();

		if(nAree == 0){
			return true;
		}else {
			return false;
		}
	}

}
