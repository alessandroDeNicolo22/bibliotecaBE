package com.bibliotecaBE.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.bibliotecaBE.data.entity.Genere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bibliotecaBE.data.dto.Request.FornitoreRequest;
import com.bibliotecaBE.data.dto.Response.FornitoreResponse;
import com.bibliotecaBE.data.entity.QFornitore;
import com.bibliotecaBE.data.entity.QOrdineacquisto;
import com.bibliotecaBE.data.entity.QPreventivo;
import com.bibliotecaBE.data.repository.FornitoreRepo;
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
		List<Genere> fornitori =  queryFactory.selectFrom(fornitore).orderBy(fornitore.id.asc()).fetch();
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
		ArrayList<Genere> list = (ArrayList<Genere>) repo.findAll();
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
		Genere oGenere = repo.findById(id).get();
		return new FornitoreResponse(oGenere.getId(),
				oGenere.getRagioneSociale(),
				oGenere.getIndirizzo(),
				oGenere.getCitta(),
				oGenere.getCap(),
				oGenere.getProvincia(),
				oGenere.getPartitaIva()

				);
	}

	@Override
	public void save(FornitoreRequest oFornitoreR){
		Genere oGenere = new Genere(
				oFornitoreR.getId(),
				oFornitoreR.getRagioneSociale(),
				oFornitoreR.getIndirizzo(),
				oFornitoreR.getCitta(),
				oFornitoreR.getCap(),
				oFornitoreR.getProvincia(),
				oFornitoreR.getPartitaIva()
				);
		repo.save(oGenere);
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
