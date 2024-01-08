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

import com.bibliotecaBE.data.dto.Request.ProfessoreRequest;
import com.bibliotecaBE.data.dto.Response.ProfessoreResponse;
import com.bibliotecaBE.data.repository.ProfessoreRepo;

@Service
@Transactional
public class ProfessoreServiceImpl implements ProfessoreService {

	@Autowired
	ProfessoreRepo repo;

	@Autowired
	EntityManager emanager;

//	@Override
//	public Page<ProfessoreResponse> getAllProgettiPage(int pageIndex, int pageSize) {
//		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//		Page<Professore> progettoPage = progettoRepo.findAll(pageRequest);
//
//		return progettoPage.map(this::entityToDTO);
//	}
//
//	private ProfessoreResponse entityToDTO(Professore professore) {
//		return new ProfessoreResponse(professore.getId(), professore.getCodice(), professore.getProgetto());
//	}

	@Override
	public ArrayList<ProfessoreResponse> getAllProfessori() {
		return entitiesToDTO((ArrayList<Professore>) repo.findAll());
	}

	private ArrayList<ProfessoreResponse> entitiesToDTO(ArrayList<Professore> elenco) {
		ArrayList<ProfessoreResponse> elencoResponse = new ArrayList<ProfessoreResponse>();
		for(Professore oProfessore : elenco) {
			elencoResponse.add(new ProfessoreResponse(oProfessore.getId(),
					oProfessore.getCognome(),
					oProfessore.getNome(),
					oProfessore.getMatricola()));
		}
		return elencoResponse;
	}

	@Override
	public ProfessoreResponse findById(Integer id) {
		Professore professore = repo.getReferenceById(id);
		return new ProfessoreResponse(professore.getId(),
				professore.getCognome(),
				professore.getNome(),
				professore.getMatricola());
	}

	@Override
	public void save(ProfessoreRequest oProfessoreRequest) {
		if (oProfessoreRequest.getId() == null) {
			repo.save(new Professore(null, oProfessoreRequest.getCognome(),
					oProfessoreRequest.getNome(),
					oProfessoreRequest.getMatricola()));
		} else {
			repo.save(this.dTOUpdateEntity(oProfessoreRequest));
		}

	}

	private Professore dTOUpdateEntity(ProfessoreRequest oProfessoreRequest) {
		Professore oProfessore = repo.findById(oProfessoreRequest.getId()).get();

		if (oProfessoreRequest.getCognome() != null) {
			oProfessore.setCognome(oProfessoreRequest.getCognome());
		}

		if (oProfessoreRequest.getMatricola() != null) {
			oProfessore.setMatricola(oProfessoreRequest.getMatricola());
		}

		if (oProfessoreRequest.getNome() != null) {
			oProfessore.setNome(oProfessoreRequest.getNome());
		}

		return oProfessore;
	}

	@Override
	public void deleteById(Integer Id) {
		repo.deleteById(Id);

	}

//	@Override
//	public Boolean check(Integer id) {
//		// TODO Auto-generated method stub
//		QOrdinedettaglio ordineDettaglio = QOrdinedettaglio.ordinedettaglio;
//		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//		long nProgetti = queryFactory.selectFrom(ordineDettaglio).where(ordineDettaglio.oProgetto.id.eq(id)).fetch().size();
//
//		if(nProgetti == 0){
//			return true;
//		}else {
//			return false;
//		}
//	}

}
