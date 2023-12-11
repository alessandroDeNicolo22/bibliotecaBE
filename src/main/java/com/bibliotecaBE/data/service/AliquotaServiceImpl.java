package com.bibliotecaBE.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.bibliotecaBE.data.entity.Autore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.bibliotecaBE.data.dto.Request.AliquotaivaRequest;
import com.bibliotecaBE.data.dto.Response.AliquotaivaResponse;
import com.bibliotecaBE.data.entity.QAliquotaiva;
import com.bibliotecaBE.data.entity.QFatturadettaglio;
import com.bibliotecaBE.data.repository.AliquotaivaRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
@Service
@Transactional
public class AliquotaServiceImpl implements AliquotaService{

	@Autowired
	AliquotaivaRepo repo;
	@Autowired
	EntityManager emanager;

	@Override
	public ArrayList<AliquotaivaResponse> getAllAliquota() {
		List<Autore> list = repo.findAll();
        return list.stream().map(a->new AliquotaivaResponse(
				a.getId(),
				a.getAliquota(),
				a.getDescrizione())).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public AliquotaivaResponse getAliquotaivaById(Integer id) {
		Autore oAutore = repo.findById(id).get();
		return new AliquotaivaResponse(oAutore.getId(), oAutore.getAliquota(), oAutore.getDescrizione());
	}

	@Override
	public void save(AliquotaivaRequest oAliquotaivaRequest) {
		Autore oAutore = new Autore(oAliquotaivaRequest.getId(),oAliquotaivaRequest.getAliquota(),oAliquotaivaRequest.getDescrizione(),oAliquotaivaRequest.getElencoDettagliFattura());
		repo.save(oAutore);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Boolean checkElimina(Integer id) {
		QFatturadettaglio fatturad = QFatturadettaglio.fatturadettaglio;
		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
		long nAliquota = queryFactory.selectFrom(fatturad).where(fatturad.oAliquotaiva.id.eq(id)).fetch().size();
		if(nAliquota==0) {
			return true;
		}else {
			return false;	
		}
	}

	@Override
	public Page<AliquotaivaResponse> getPageAliquote(Integer pageIndex, Integer pageSize) {
		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

		QAliquotaiva aliquota = QAliquotaiva.aliquotaiva;
		JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
		List<Autore> aliquote =  queryFactory.selectFrom(aliquota).orderBy(aliquota.id.asc()).fetch();
		ArrayList<AliquotaivaResponse> elencoResponse = aliquote.stream().map(a->new AliquotaivaResponse(a.getId(), 
				a.getAliquota(),a.getDescrizione())).collect(Collectors.toCollection(ArrayList::new));

		int startIndex = pageIndex * pageSize;
		if (startIndex >= elencoResponse.size()) {
			return Page.empty(); 
		}
		int endIndex = Math.min(startIndex + pageSize, aliquote.size());
		List<AliquotaivaResponse> pageItems = elencoResponse.subList(startIndex, endIndex);

		return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
	}

}
