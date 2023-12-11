package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.FatturapassivaRequest;
import com.europCarBE.data.dto.Request.FilterDateRequest;
import com.europCarBE.data.dto.Response.FatturadettaglioResponse;
import com.europCarBE.data.dto.Response.FatturapassivaResponse;
import com.europCarBE.data.entity.*;
import com.europCarBE.data.repository.FatturadettaglioRepo;
import com.europCarBE.data.repository.FatturapassivaRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class FatturapassivaServiceImpl implements FatturapassivaService{
    @Autowired
    FatturapassivaRepo repo;

    @Autowired
    EntityManager emanager;

    @Autowired
    FatturadettaglioRepo dettaglioRepo;

    private final QFatturadettaglio fatturadettaglio = QFatturadettaglio.fatturadettaglio;

    @Override
    public ArrayList<FatturapassivaResponse> getAll() {
        ArrayList<Fatturapassiva> list = (ArrayList<Fatturapassiva>) repo.findAll();
        return list.stream().map(f->new FatturapassivaResponse(f.getId(),
           f.getData(),
                f.getNumero(),
                f.getDescrizione(),
                f.getOFornitore())).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Page<FatturapassivaResponse> getAllFatture(int id, int pageIndex, int pageSize) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QFatturapassiva fatturapassiva = QFatturapassiva.fatturapassiva;
        List<Fatturapassiva> list = queryFactory.selectFrom(fatturapassiva).where(fatturapassiva.oFornitore.id.eq(id)).
                orderBy(fatturapassiva.id.asc()).stream().toList();
        return getFatturapassivaResponses(pageIndex, pageSize, pageRequest, list);
    }

    @Override
    public FatturapassivaResponse getFatturaById(Integer id) {
        Fatturapassiva fatturapassiva = repo.getReferenceById(id);
        return new FatturapassivaResponse(fatturapassiva.getId(),
                fatturapassiva.getData(),
                fatturapassiva.getNumero(),
                fatturapassiva.getDescrizione(),
                fatturapassiva.getOFornitore());
    }

    @Override
    public void save(FatturapassivaRequest fatturapassivaRequest) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        if(!fatturapassivaRequest.getId().equals(0)){
            queryFactory.delete(fatturadettaglio).where(fatturadettaglio.oFatturapassiva.id.eq(fatturapassivaRequest.getId())).execute();
            Fatturapassiva fatturapassiva = new Fatturapassiva(fatturapassivaRequest.getId(),
                    fatturapassivaRequest.getData(),
                    fatturapassivaRequest.getNumero(),
                    fatturapassivaRequest.getDescrizione(),
                    fatturapassivaRequest.getOFornitore());
            repo.save(fatturapassiva);
        }
    Fatturapassiva fatturapassiva = new Fatturapassiva(fatturapassivaRequest.getId(),
            fatturapassivaRequest.getData(),
            fatturapassivaRequest.getNumero(),
            fatturapassivaRequest.getDescrizione(),
            fatturapassivaRequest.getOFornitore());
    repo.save(fatturapassiva);
    }

    @Override
    public void deleteById(Integer id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        QFatturadettaglio qFatturadettaglio = QFatturadettaglio.fatturadettaglio;
        queryFactory.delete(qFatturadettaglio).where(qFatturadettaglio.oFatturapassiva.id.eq(id)).execute();
        repo.deleteById(id);
    }

    @Override
    public Page<FatturadettaglioResponse> findDettagli(Integer id, int pageIndex, int pageSize) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Fatturadettaglio> list = queryFactory.selectFrom(fatturadettaglio).where(fatturadettaglio.oFatturapassiva.id.eq(id)).orderBy(fatturadettaglio.id.asc()).stream().toList();
        ArrayList<FatturadettaglioResponse> elencoResponse = list.stream().map(f -> new FatturadettaglioResponse(f.getId(),
                f.getOAliquotaiva(),
                f.getOFatturapassiva(),
                f.getOPreventivo(),
                f.getOSpesainvestimento(),
                f.getDettaglioFattura(),
                f.getImporto())).collect(Collectors.toCollection(ArrayList::new));
        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, list.size());
        List<FatturadettaglioResponse> pageItems = elencoResponse.subList(startIndex, endIndex);
        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }

    @Override
    public ArrayList<FatturadettaglioResponse> findDettagli1(Integer id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Fatturadettaglio> list = queryFactory.selectFrom(fatturadettaglio).where(fatturadettaglio.oFatturapassiva.id.eq(id)).orderBy(fatturadettaglio.id.asc()).stream().toList();
        return list.stream().map(f -> new FatturadettaglioResponse(f.getId(),
                f.getOAliquotaiva(),
                f.getOFatturapassiva(),
                f.getOPreventivo(),
                f.getOSpesainvestimento(),
                f.getDettaglioFattura(),
                f.getImporto())).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Page<FatturapassivaResponse> filterFatture(FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        QFatturapassiva fatturapassiva = QFatturapassiva.fatturapassiva;
        if (filterDateRequest.getType().equals("Fornitore")) {
            List<Fatturapassiva> list = queryFactory.selectFrom(fatturapassiva).
                    where(fatturapassiva.oFornitore.id.eq(filterDateRequest.getId()).
                            and(fatturapassiva.data.between(filterDateRequest.getStartDate(), filterDateRequest.getEndDate()))).
                    stream().toList();
            return createPage(list,pageIndex,pageSize);
        } else if (filterDateRequest.getType().equals("Sottocategoria")) {
            List<Fatturapassiva> list= repo.findPageByIDSottocategoria(filterDateRequest.getStartDate(), filterDateRequest.getEndDate(), filterDateRequest.getId());
            return createPage(list,pageIndex,pageSize);
        } else if (filterDateRequest.getType().equals("Preventivo")) {
            List<Fatturapassiva> list= repo.findPageByIDPreventivo(filterDateRequest.getStartDate(), filterDateRequest.getEndDate(), filterDateRequest.getId());
            return createPage(list,pageIndex,pageSize);
        }else {
            return null;
        }
    }
    private Page<FatturapassivaResponse> createPage(List<Fatturapassiva> list, Integer pageIndex, Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return getFatturapassivaResponses(pageIndex, pageSize, pageRequest, list);
    }

    @Override
    public Page<FatturapassivaResponse> filterOnlyDate(FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QFatturapassiva fatturapassiva = QFatturapassiva.fatturapassiva;
        List<Fatturapassiva> list= queryFactory.selectFrom(fatturapassiva).
                where(fatturapassiva.data.between(filterDateRequest.getStartDate(),filterDateRequest.getEndDate())).
                stream().toList();
        return getFatturapassivaResponses(pageIndex, pageSize, pageRequest, list);
    }

    private Page<FatturapassivaResponse> getFatturapassivaResponses(int pageIndex, int pageSize, PageRequest pageRequest, List<Fatturapassiva> list) {
        ArrayList<FatturapassivaResponse> elencoResponse = list.stream().map(f-> new FatturapassivaResponse(f.getId(),
                f.getData(),
                f.getNumero(),
                f.getDescrizione(),
                f.getOFornitore()
        )).collect(Collectors.toCollection(ArrayList::new));
        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, list.size());
        List<FatturapassivaResponse> pageItems = elencoResponse.subList(startIndex, endIndex);
        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }
}
