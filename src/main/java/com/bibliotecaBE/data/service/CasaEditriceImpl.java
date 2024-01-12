package com.bibliotecaBE.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.bibliotecaBE.data.entity.Casaeditrice;
import com.bibliotecaBE.data.entity.QCasaeditrice;
import com.bibliotecaBE.data.entity.QLibro;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

	@Override
	public Page<CasaeditriceResponse> getAllCEPage(int pageIndex, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

		QCasaeditrice casaeditrice = QCasaeditrice.casaeditrice;
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(emanager);

		List<Casaeditrice> list = jpaQueryFactory.selectFrom(casaeditrice).orderBy(casaeditrice.id.asc()).fetch();
		ArrayList<CasaeditriceResponse> responses = (ArrayList<CasaeditriceResponse>) list.stream().map(ce->new CasaeditriceResponse(ce.getId(), ce.getNome())).collect(Collectors.toList());

		int startIndex = pageIndex * pageSize;
		if (startIndex >= responses.size()) {
			return Page.empty();
		}
		int endIndex = Math.min(startIndex + pageSize, list.size());

		List<CasaeditriceResponse> pageItems = responses.subList(startIndex, endIndex);

		return new PageImpl<>(pageItems, pageRequest, responses.size());
    }

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
	public Boolean checkDelete(Integer id) {
		QLibro libro  = QLibro.libro;
		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
		long nCE = queryFactory.selectFrom(libro).where(libro.oCasaeditrice.id.eq(id)).fetch().size();

        return nCE == 0;
	}

}
