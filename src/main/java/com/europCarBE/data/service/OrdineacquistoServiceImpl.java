package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.FilterDateRequest;
import com.europCarBE.data.dto.Request.OrdineacquistoRequest;
import com.europCarBE.data.dto.Response.OrdineacquistoResponse;
import com.europCarBE.data.dto.Response.OrdinedettaglioResponse;
import com.europCarBE.data.entity.*;
import com.europCarBE.data.repository.OrdineacquistoRepo;
import com.europCarBE.data.repository.OrdinedettaglioRepo;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class OrdineacquistoServiceImpl implements OrdineacquistoService{
   @Autowired
    OrdineacquistoRepo repo;

   @Autowired
   EntityManager emanager;

   @Autowired
    OrdinedettaglioRepo dettaglioRepo;

    @Override
    public ArrayList<OrdineacquistoResponse> getAll() {
        ArrayList<Ordineacquisto> list = (ArrayList<Ordineacquisto>) repo.findAll();
        return list.stream().map(o->new OrdineacquistoResponse(o.getId(),
                o.getImporto(),
                o.getOrdineAcquisto(),
                o.getData(),
                o.getOFornitore())).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Page<OrdineacquistoResponse> getAllOrdini(int id,int pageIndex, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QOrdineacquisto ordineacquisto = QOrdineacquisto.ordineacquisto;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Ordineacquisto> list = queryFactory.selectFrom(ordineacquisto).where(ordineacquisto.oFornitore.id.eq(id)).orderBy(ordineacquisto.id.asc()).stream().toList();
        return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);
    }

    private Page<OrdineacquistoResponse> getOrdineacquistoResponses(int pageIndex, int pageSize, PageRequest pageRequest, List<Ordineacquisto> list) {
        ArrayList<OrdineacquistoResponse> elencoResponse = list.stream().map(o -> new OrdineacquistoResponse(
                o.getId(),
                o.getImporto(),
                o.getOrdineAcquisto(),
                o.getData(),
                o.getOFornitore())).collect(Collectors.toCollection(ArrayList::new));

        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, list.size());
        List<OrdineacquistoResponse> pageItems = elencoResponse.subList(startIndex, endIndex);

        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }

    @Override
    public OrdineacquistoResponse getOrdineById(Integer id) {
        Ordineacquisto ordineacquisto = repo.getReferenceById(id);
        return new OrdineacquistoResponse(ordineacquisto.getId(),
                ordineacquisto.getImporto(),
                ordineacquisto.getOrdineAcquisto(),
                ordineacquisto.getData(),
                ordineacquisto.getOFornitore());
    }

    @Override
    public void save(OrdineacquistoRequest ordineacquistoRequest) {
        if (ordineacquistoRequest.getId() != 0) {
            QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
            JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
            queryFactory.delete(ordinedettaglio).where(ordinedettaglio.oOrdineAcquisto.id.eq(ordineacquistoRequest.getId())).execute();
            repo.save(new Ordineacquisto(ordineacquistoRequest.getId(),
                    ordineacquistoRequest.getImporto(),
                    ordineacquistoRequest.getOrdineacquisto(),
                    ordineacquistoRequest.getData(),
                    ordineacquistoRequest.getOFornitore()));
        } else {
            repo.save(new Ordineacquisto(0,
                    ordineacquistoRequest.getImporto(),
                    ordineacquistoRequest.getOrdineacquisto(),
                    ordineacquistoRequest.getData(),
                    ordineacquistoRequest.getOFornitore()));
        }
    }

    @Override
    public void deleteById(Integer id) {
        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        queryFactory.delete(ordinedettaglio).where(ordinedettaglio.oOrdineAcquisto.id.eq(id)).execute();
        repo.deleteById(id);
    }

    @Override
    public Page<OrdinedettaglioResponse> findDettagli(Integer id, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Ordinedettaglio> list = queryFactory.selectFrom(ordinedettaglio).where(ordinedettaglio.oOrdineAcquisto.id.eq(id)).orderBy(ordinedettaglio.id.asc()).stream().toList();
        ArrayList<OrdinedettaglioResponse> elencoResponse = list.stream().map(o -> new OrdinedettaglioResponse(o.getId(),
                o.getOSpesaInvestimento(),
                o.getOProgetto(),
                o.getOOrdineAcquisto(),
                o.getImporto(),
                o.getQuantita())).collect(Collectors.toCollection(ArrayList::new));
        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, list.size());
        List<OrdinedettaglioResponse> pageItems = elencoResponse.subList(startIndex, endIndex);
        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }

    @Override
    public ArrayList<OrdinedettaglioResponse> findDettagli1(Integer id) {
        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Ordinedettaglio> list = queryFactory.selectFrom(ordinedettaglio).where(ordinedettaglio.oOrdineAcquisto.id.eq(id)).orderBy(ordinedettaglio.id.asc()).stream().toList();
        return  list.stream().map(o -> new OrdinedettaglioResponse(o.getId(),
                o.getOSpesaInvestimento(),
                o.getOProgetto(),
                o.getOOrdineAcquisto(),
                o.getImporto(),
                o.getQuantita())).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Page<OrdineacquistoResponse> filterOrdini(FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QOrdineacquisto ordineacquisto = QOrdineacquisto.ordineacquisto;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        if(filterDateRequest.getType().equals("Fornitore")) {
            List<Ordineacquisto> list = queryFactory.selectFrom(ordineacquisto).where(ordineacquisto.oFornitore.id.eq(filterDateRequest.getId()).
                    and(ordineacquisto.data.after(filterDateRequest.getStartDate()).
                            and(ordineacquisto.data.before(filterDateRequest.getEndDate())))).stream().toList();
            return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);

        }else if(filterDateRequest.getType().equals("Progetto")){
            List<Ordineacquisto> list = repo.findPageByIDProgetto(filterDateRequest.getId(), filterDateRequest.getStartDate(), filterDateRequest.getEndDate());
            return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);
        }else if(filterDateRequest.getType().equals("Sottocategoria")){
            List<Ordineacquisto> list = repo.findPageByIDSottocategoria(filterDateRequest.getId(), filterDateRequest.getStartDate(), filterDateRequest.getEndDate());
            return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);
        }else{
            return null;
        }
    }

    @Override
    public Page<OrdineacquistoResponse> filterOnlyDate(FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QOrdineacquisto ordineacquisto = QOrdineacquisto.ordineacquisto;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Ordineacquisto> list = queryFactory.selectFrom(ordineacquisto).where(ordineacquisto.data.between(filterDateRequest.getStartDate(),filterDateRequest.getEndDate())).stream().toList();
        return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);
    }
}
