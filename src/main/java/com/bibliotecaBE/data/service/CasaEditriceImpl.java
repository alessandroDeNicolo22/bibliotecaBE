package com.bibliotecaBE.data.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.bibliotecaBE.data.entity.Casaeditrice;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bibliotecaBE.data.dto.Request.CasaeditriceRequest;
import com.bibliotecaBE.data.dto.Response.CasaeditriceResponse;
import com.bibliotecaBE.data.repository.CasaeditriceRepo;

@Service
@Transactional
public class CasaEditriceImpl implements CasaEditriceService {

	@Autowired
	CasaeditriceRepo casaeditriceRepo;

	@Autowired
	EntityManager emanager;

	@Override
	public ArrayList<CasaeditriceResponse> getAllCaseEditrici() {
		return EntitiesToDTO((ArrayList<Casaeditrice>) casaeditriceRepo.findAll());
	}

	private ArrayList<CasaeditriceResponse> EntitiesToDTO(ArrayList<Casaeditrice> elenco) {
		ArrayList<CasaeditriceResponse> elencoResponse = new ArrayList<CasaeditriceResponse>();
		for(Casaeditrice oCasaeditrice : elenco) {
			elencoResponse.add(new CasaeditriceResponse(oCasaeditrice.getId(), oCasaeditrice.getNome()));
		}
		return elencoResponse;
	}

//	@Override
//	public Page<CasaeditriceResponse> getAllAreePage(int pageIndex, int pageSize) {
//		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//		Page<Casaeditrice> areaPage = casaeditriceRepo.findAll(pageRequest);
//		return areaPage.map(this::entityToDTO);
//	}

//	public CasaeditriceResponse entityToDTO(Casaeditrice oCasaeditrice) {
//		return new CasaeditriceResponse(oCasaeditrice.getId(), oCasaeditrice.getCodice(), oCasaeditrice.getArea());
//	}

	@Override
	public CasaeditriceResponse getCasaEditriceById(Integer id) {
		Casaeditrice oCasaeditrice = casaeditriceRepo.findById(id).get();
		return new CasaeditriceResponse(oCasaeditrice.getId(),oCasaeditrice.getNome());
	}

	@Override
	public void save(CasaeditriceRequest casaeditriceRequest) {
		if (casaeditriceRequest.getId() == null) {
			casaeditriceRepo.save(new Casaeditrice(casaeditriceRequest.getId(), casaeditriceRequest.getNome()));
		} else {
			casaeditriceRepo.save(this.dTOUpdateEntity(casaeditriceRequest));
		}
	}

	private Casaeditrice dTOUpdateEntity(CasaeditriceRequest casaeditriceRequest) {
		Casaeditrice oCasaeditrice = casaeditriceRepo.findById(casaeditriceRequest.getId()).get();
		if (casaeditriceRequest.getNome() != null) {
			oCasaeditrice.setNome(casaeditriceRequest.getNome());
		}
		return oCasaeditrice;
	}

	@Override
	public void deleteById(Integer id) {
		casaeditriceRepo.deleteById(id);

	}

//	@Override
//	public Boolean checkDelete(Integer id) {
//		QSottocategoria oSottocategoria = QSottocategoria.sottocategoria1;
//		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//		long nAree = queryFactory.selectFrom(oSottocategoria).where(oSottocategoria.oArea.id.eq(id)).fetch().size();
//
//		if(nAree == 0){
//			return true;
//		}else {
//			return false;
//		}
//	}

}
