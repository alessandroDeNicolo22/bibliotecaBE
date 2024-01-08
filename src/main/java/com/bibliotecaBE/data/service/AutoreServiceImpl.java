package com.bibliotecaBE.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.bibliotecaBE.data.dto.Response.AutoreResponse;
import com.bibliotecaBE.data.entity.Autore;
import com.bibliotecaBE.data.repository.AutoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.bibliotecaBE.data.dto.Request.AutoreRequest;
import com.bibliotecaBE.data.entity.QAliquotaiva;
import com.bibliotecaBE.data.entity.QFatturadettaglio;
import com.querydsl.jpa.impl.JPAQueryFactory;
@Service
@Transactional
public class AutoreServiceImpl implements AutoreService {

	@Autowired
	AutoreRepo repo;
	@Autowired
	EntityManager emanager;

	@Override
	public ArrayList<AutoreResponse> getAllAutori() {
		List<Autore> list = repo.findAll();
        return list.stream().map(a->new AutoreResponse(
				a.getId(),
				a.getNome(),
				a.getCognome())).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public AutoreResponse getAutoreById(Integer id) {
		Autore oAutore = repo.findById(id).get();
		return new AutoreResponse(oAutore.getId(),oAutore.getCognome(),oAutore.getNome());
	}

	@Override
	public void save(AutoreRequest oAutoreRequest) {
		Autore oAutore = new Autore(oAutoreRequest.getId(), oAutoreRequest.getCognome(), oAutoreRequest.getNome());
		repo.save(oAutore);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

//	@Override
//	public Boolean checkElimina(Integer id) {
////		QFatturadettaglio fatturad = QFatturadettaglio.fatturadettaglio;
////		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
////		long nAliquota = queryFactory.selectFrom(fatturad).where(fatturad.oAliquotaiva.id.eq(id)).fetch().size();
////		if(nAliquota==0) {
////			return true;
////		}else {
////			return false;
////		}
//	}

//	@Override
//	public Page<AutoreResponse> getPageAutori(Integer pageIndex, Integer pageSize) {
//		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//
//		QAutore autore = QAliquotaiva.aliquotaiva;
//		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//		List<Autore> aliquote =  queryFactory.selectFrom(aliquota).orderBy(aliquota.id.asc()).fetch();
//		ArrayList<AutoreResponse> elencoResponse = aliquote.stream().map(a->new AutoreResponse(a.getId(),
//				a.getAliquota(),a.getDescrizione())).collect(Collectors.toCollection(ArrayList::new));
//
//		int startIndex = pageIndex * pageSize;
//		if (startIndex >= elencoResponse.size()) {
//			return Page.empty();
//		}
//		int endIndex = Math.min(startIndex + pageSize, aliquote.size());
//		List<AutoreResponse> pageItems = elencoResponse.subList(startIndex, endIndex);
//
//		return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
//	}

}
