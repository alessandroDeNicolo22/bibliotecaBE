package com.europCarBE.data.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.europCarBE.data.entity.QOrdinedettaglio;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.europCarBE.data.dto.Request.ProgettoRequest;
import com.europCarBE.data.dto.Response.ProgettoResponse;
import com.europCarBE.data.entity.Area;
import com.europCarBE.data.entity.Progetto;
import com.europCarBE.data.repository.ProgettoRepo;

@Service
@Transactional
public class ProgettoServiceImpl implements ProgettoService{

	@Autowired
	ProgettoRepo progettoRepo;

	@Autowired
	EntityManager emanager;

	@Override
	public Page<ProgettoResponse> getAllProgettiPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
		Page<Progetto> progettoPage = progettoRepo.findAll(pageRequest);

		return progettoPage.map(this::entityToDTO);
	}

	private ProgettoResponse entityToDTO(Progetto progetto) {
		return new ProgettoResponse(progetto.getId(), progetto.getCodice(), progetto.getProgetto());
	}

	@Override
	public ArrayList<ProgettoResponse> getAllProgetti() {
		// TODO Auto-generated method stub
		return entitiesToDTO((ArrayList<Progetto>)progettoRepo.findAll());
	}

	private ArrayList<ProgettoResponse> entitiesToDTO(ArrayList<Progetto> elenco) {
		// TODO Auto-generated method stub
		ArrayList<ProgettoResponse> elencoResponse = new ArrayList<ProgettoResponse>();
		for(Progetto oProgetto: elenco) {
			elencoResponse.add(new ProgettoResponse(oProgetto.getId(), oProgetto.getCodice(), oProgetto.getProgetto()));
		}
		return elencoResponse;
	}

	@Override
	public ProgettoResponse findById(Integer id) {
		// TODO Auto-generated method stub
		Progetto progetto = progettoRepo.findById(id).get();
		return new ProgettoResponse(progetto.getId(), progetto.getCodice(), progetto.getProgetto());
	}

	@Override
	public void save(ProgettoRequest oProgettoRequest) {
		// TODO Auto-generated method stub
		if (oProgettoRequest.getId() == null) {
			progettoRepo.save(new Progetto(null, oProgettoRequest.getCodice(), oProgettoRequest.getProgetto()));
		} else {
			progettoRepo.save(this.dTOUpdateEntity(oProgettoRequest));
		}

	}

	private Progetto dTOUpdateEntity(ProgettoRequest oProgettoRequest) {
		// TODO Auto-generated method stub
		Progetto oProgetto = progettoRepo.findById(oProgettoRequest.getId()).get();

		if (oProgettoRequest.getCodice() != null) {
			oProgetto.setCodice(oProgettoRequest.getCodice());
		}

		if (oProgettoRequest.getProgetto() != null) {
			oProgetto.setProgetto(oProgettoRequest.getProgetto());
		}

		return oProgetto;
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
