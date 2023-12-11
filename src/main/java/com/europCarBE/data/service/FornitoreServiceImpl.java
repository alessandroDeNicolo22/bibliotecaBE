package com.europCarBE.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.europCarBE.data.dto.Request.FornitoreRequest;
import com.europCarBE.data.dto.Response.FornitoreResponse;
import com.europCarBE.data.entity.Fornitore;
import com.europCarBE.data.entity.QFornitore;
import com.europCarBE.data.entity.QOrdineacquisto;
import com.europCarBE.data.entity.QPreventivo;
import com.europCarBE.data.repository.FornitoreRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;

@Service
@Transactional
public class FornitoreServiceImpl implements FornitoreService{
	@Autowired
	FornitoreRepo repo;
	@Autowired
	EntityManager emanager;


	@Override
	public Page<FornitoreResponse> getAllFornitoriPage(int pageIndex, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
		QFornitore fornitore = QFornitore.fornitore;
		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
		List<Fornitore> fornitori =  queryFactory.selectFrom(fornitore).orderBy(fornitore.id.asc()).fetch();
		ArrayList<FornitoreResponse> elencoResponse = fornitori.stream().map(f->new FornitoreResponse(
				f.getId(),
				f.getRagioneSociale(),
				f.getIndirizzo(),
				f.getCitta(),
				f.getCap(),
				f.getProvincia(),
				f.getPartitaIva())).collect(Collectors.toCollection(ArrayList::new));

		int startIndex = pageIndex * pageSize;
		if (startIndex >= elencoResponse.size()) {
			return Page.empty(); 
		}
		int endIndex = Math.min(startIndex + pageSize, fornitori.size());
		List<FornitoreResponse> pageItems = elencoResponse.subList(startIndex, endIndex);

		return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
	}

	@Override
	public ArrayList<FornitoreResponse> getAllFornitori() {
		ArrayList<Fornitore> list = (ArrayList<Fornitore>) repo.findAll();
		return list.stream().map(f->new FornitoreResponse(
				f.getId(),
				f.getRagioneSociale(),
				f.getIndirizzo(),
				f.getCitta(),
				f.getCap(),
				f.getProvincia(),
				f.getPartitaIva()
				)).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public FornitoreResponse getFornitoreById(Integer id) {	
		Fornitore oFornitore = repo.findById(id).get();
		return new FornitoreResponse(oFornitore.getId(),
				oFornitore.getRagioneSociale(),
				oFornitore.getIndirizzo(),
				oFornitore.getCitta(),
				oFornitore.getCap(),
				oFornitore.getProvincia(),
				oFornitore.getPartitaIva()

				);
	}

	@Override
	public void save(FornitoreRequest oFornitoreR){
		Fornitore oFornitore = new Fornitore(
				oFornitoreR.getId(),
				oFornitoreR.getRagioneSociale(),
				oFornitoreR.getIndirizzo(),
				oFornitoreR.getCitta(),
				oFornitoreR.getCap(),
				oFornitoreR.getProvincia(),
				oFornitoreR.getPartitaIva()
				);
		repo.save(oFornitore);
	}
//oFornitoreR.getElencoFatture(),
//		oFornitoreR.getElencoPreventivi(),
//		oFornitoreR.getElencoOrdini()
	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Boolean check(Integer id) {
		QOrdineacquisto oacquisto = QOrdineacquisto.ordineacquisto;
		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
		long nFornitori = queryFactory.selectFrom(oacquisto).where(oacquisto.oFornitore.id.eq(id)).fetch().size();
		QPreventivo preventivo = QPreventivo.preventivo1;
		long nForn = queryFactory.selectFrom(preventivo).where(preventivo.oFornitore.id.eq(id)).fetch().size();
		if(nFornitori+nForn == 0) {
			return true;
		}else {
			return false;
		}

	}

}
