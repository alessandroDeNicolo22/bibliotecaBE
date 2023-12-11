package com.bibliotecaBE.data.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.bibliotecaBE.data.entity.Casaeditrice;
import com.bibliotecaBE.data.entity.QSottocategoria;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bibliotecaBE.data.dto.Request.AreaRequest;
import com.bibliotecaBE.data.dto.Response.CasaeditriceResponse;
import com.bibliotecaBE.data.repository.CasaeditriceRepo;

@Service
@Transactional
public class AreaServiceImpl implements AreaService{

	@Autowired
	CasaeditriceRepo casaeditriceRepo;

	@Autowired
	EntityManager emanager;

	@Override
	public ArrayList<CasaeditriceResponse> getAllAree() {
		// TODO Auto-generated method stub
		return EntitiesToDTO((ArrayList<Casaeditrice>) casaeditriceRepo.findAll());
	}

	private ArrayList<CasaeditriceResponse> EntitiesToDTO(ArrayList<Casaeditrice> elenco) {
		// TODO Auto-generated method stub
		ArrayList<CasaeditriceResponse> elencoResponse = new ArrayList<CasaeditriceResponse>();
		for(Casaeditrice oCasaeditrice : elenco) {
			elencoResponse.add(new CasaeditriceResponse(oCasaeditrice.getId(), oCasaeditrice.getCodice(), oCasaeditrice.getArea()));
		}
		return elencoResponse;
	}

	@Override
	public Page<CasaeditriceResponse> getAllAreePage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
		Page<Casaeditrice> areaPage = casaeditriceRepo.findAll(pageRequest);
		return areaPage.map(this::entityToDTO);
	}

	public CasaeditriceResponse entityToDTO(Casaeditrice oCasaeditrice) {
		return new CasaeditriceResponse(oCasaeditrice.getId(), oCasaeditrice.getCodice(), oCasaeditrice.getArea());
	}

	@Override
	public CasaeditriceResponse getAreaById(Integer id) {
		// TODO Auto-generated method stub
		Casaeditrice oCasaeditrice = casaeditriceRepo.findById(id).get();
		return new CasaeditriceResponse(oCasaeditrice.getId(), oCasaeditrice.getCodice(), oCasaeditrice.getArea());
	}

	@Override
	public void save(AreaRequest area) {
		// TODO Auto-generated method stub
		if (area.getId() == null) {
			casaeditriceRepo.save(new Casaeditrice(area.getId(), area.getCodice(), area.getArea()));
		} else {
			casaeditriceRepo.save(this.dTOUpdateEntity(area));
		}
	}

	private Casaeditrice dTOUpdateEntity(AreaRequest area) {
		// TODO Auto-generated method stub
		Casaeditrice oCasaeditrice = casaeditriceRepo.findById(area.getId()).get();

		if (area.getCodice() != null) {
			oCasaeditrice.setCodice(area.getCodice());
		}

		if (area.getArea() != null) {
			oCasaeditrice.setArea(area.getArea());
		}

		return oCasaeditrice;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		casaeditriceRepo.deleteById(id);

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
