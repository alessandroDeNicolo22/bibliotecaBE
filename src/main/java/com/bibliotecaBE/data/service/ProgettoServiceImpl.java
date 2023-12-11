package com.bibliotecaBE.data.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.bibliotecaBE.data.entity.Professore;
import com.bibliotecaBE.data.entity.QOrdinedettaglio;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bibliotecaBE.data.dto.Request.ProgettoRequest;
import com.bibliotecaBE.data.dto.Response.ProgettoResponse;
import com.bibliotecaBE.data.repository.ProfessoreRepo;

@Service
@Transactional
public class ProgettoServiceImpl implements ProgettoService{

	@Autowired
	ProfessoreRepo progettoRepo;

	@Autowired
	EntityManager emanager;

	@Override
	public Page<ProgettoResponse> getAllProgettiPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
		Page<Professore> progettoPage = progettoRepo.findAll(pageRequest);

		return progettoPage.map(this::entityToDTO);
	}

	private ProgettoResponse entityToDTO(Professore professore) {
		return new ProgettoResponse(professore.getId(), professore.getCodice(), professore.getProgetto());
	}

	@Override
	public ArrayList<ProgettoResponse> getAllProgetti() {
		// TODO Auto-generated method stub
		return entitiesToDTO((ArrayList<Professore>)progettoRepo.findAll());
	}

	private ArrayList<ProgettoResponse> entitiesToDTO(ArrayList<Professore> elenco) {
		// TODO Auto-generated method stub
		ArrayList<ProgettoResponse> elencoResponse = new ArrayList<ProgettoResponse>();
		for(Professore oProfessore : elenco) {
			elencoResponse.add(new ProgettoResponse(oProfessore.getId(), oProfessore.getCodice(), oProfessore.getProgetto()));
		}
		return elencoResponse;
	}

	@Override
	public ProgettoResponse findById(Integer id) {
		// TODO Auto-generated method stub
		Professore professore = progettoRepo.findById(id).get();
		return new ProgettoResponse(professore.getId(), professore.getCodice(), professore.getProgetto());
	}

	@Override
	public void save(ProgettoRequest oProgettoRequest) {
		// TODO Auto-generated method stub
		if (oProgettoRequest.getId() == null) {
			progettoRepo.save(new Professore(null, oProgettoRequest.getCodice(), oProgettoRequest.getProgetto()));
		} else {
			progettoRepo.save(this.dTOUpdateEntity(oProgettoRequest));
		}

	}

	private Professore dTOUpdateEntity(ProgettoRequest oProgettoRequest) {
		// TODO Auto-generated method stub
		Professore oProfessore = progettoRepo.findById(oProgettoRequest.getId()).get();

		if (oProgettoRequest.getCodice() != null) {
			oProfessore.setCodice(oProgettoRequest.getCodice());
		}

		if (oProgettoRequest.getProgetto() != null) {
			oProfessore.setProgetto(oProgettoRequest.getProgetto());
		}

		return oProfessore;
	}

	@Override
	public void deleteById(Integer Id) {
		// TODO Auto-generated method stub
		progettoRepo.deleteById(Id);

	}

	@Override
	public Boolean check(Integer id) {
		// TODO Auto-generated method stub
		QOrdinedettaglio ordineDettaglio = QOrdinedettaglio.ordinedettaglio;
		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
		long nProgetti = queryFactory.selectFrom(ordineDettaglio).where(ordineDettaglio.oProgetto.id.eq(id)).fetch().size();

		if(nProgetti == 0){
			return true;
		}else {
			return false;
		}
	}

}
