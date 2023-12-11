package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.SottocategoriaRequest;
import com.europCarBE.data.dto.Response.FornitoreResponse;
import com.europCarBE.data.dto.Response.SottocategoriaResponse;
import com.europCarBE.data.entity.*;
import com.europCarBE.data.repository.FornitoreRepo;
import com.europCarBE.data.repository.OrdinedettaglioRepo;
import com.europCarBE.data.repository.SottocategoriaRepo;
import com.europCarBE.data.repository.SpesainvestimentoRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SottocategoriaServiceImpl implements SottocategoriaService {
    @Autowired
    SottocategoriaRepo repo;
    @Autowired
    EntityManager emanager;
    @Autowired
    OrdinedettaglioRepo ordinedettaglioRepo;
    @Autowired
    SpesainvestimentoRepo repoSpesa;
    @Override
    public Page<SottocategoriaResponse> getAllSottocategoriaPage(int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QSottocategoria sottocategoria = QSottocategoria.sottocategoria1;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Sottocategoria> sottocategoriaList = queryFactory.selectFrom(sottocategoria).orderBy(sottocategoria.id.asc()).fetch();
        return getSottocategoriaResponses(pageIndex, pageSize, pageRequest, sottocategoriaList);
    }

    private Page<SottocategoriaResponse> getSottocategoriaResponses(int pageIndex, int pageSize, PageRequest pageRequest, List<Sottocategoria> sottocategoriaList) {
        ArrayList<SottocategoriaResponse> elencoResponse = sottocategoriaList.stream().map(s -> new SottocategoriaResponse(
                s.getId(),
                s.getCodice(),
                s.getSottocategoria(),
                s.getBudget(),
                s.getBudgetSpeso(),
                s.getOArea())).collect(Collectors.toCollection(ArrayList::new));

        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, sottocategoriaList.size());
        List<SottocategoriaResponse> pageItems = elencoResponse.subList(startIndex, endIndex);

        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }

    @Override
    public ArrayList<SottocategoriaResponse> getAllSottocategoria() {
        ArrayList<Sottocategoria> list = (ArrayList<Sottocategoria>) repo.findAll();
        return list.stream().map(s -> new SottocategoriaResponse(
                s.getId(),
                s.getCodice(),
                s.getSottocategoria(),
                s.getBudget(),
                s.getBudgetSpeso(),
                s.getOArea()
        )).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public SottocategoriaResponse getSottocategoriaById(Integer id) {
        Sottocategoria sottocategoria = repo.getReferenceById(id);
        return new SottocategoriaResponse(
                sottocategoria.getId(),
                sottocategoria.getCodice(),
                sottocategoria.getSottocategoria(),
                sottocategoria.getBudget(),
                sottocategoria.getBudgetSpeso(),
                sottocategoria.getOArea()
        );
    }

    @Override
    public void save(SottocategoriaRequest oSottocategoriaRequest) {
        repo.save(new Sottocategoria(oSottocategoriaRequest.getId(),
                oSottocategoriaRequest.getCodice(),
                oSottocategoriaRequest.getSottocategoria(),
                oSottocategoriaRequest.getBudget(),
                oSottocategoriaRequest.getBudgetSpeso(),
                oSottocategoriaRequest.getOArea()));
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Boolean check(Integer id) {
        QSpesainvestimento spesainvestimento = QSpesainvestimento.spesainvestimento;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        long nSottocategoria = queryFactory.selectFrom(spesainvestimento).where(spesainvestimento.oSottocategoria.id
                .eq(id)).stream().count();
        if(nSottocategoria == 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Page<SottocategoriaResponse> getSottocategoriaByAreaPage(int id,int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QSottocategoria sottocategoria = QSottocategoria.sottocategoria1;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Sottocategoria> sottocategoriaList = queryFactory.selectFrom(sottocategoria).where(sottocategoria.oArea.id.eq(id)).orderBy(sottocategoria.id.asc()).fetch();
        return getSottocategoriaResponses(pageIndex, pageSize, pageRequest, sottocategoriaList);
    }

   @Override
    public void setBudget(Date firstDate, Date endDate) {
        java.sql.Date primaData = new java.sql.Date(firstDate.getTime());
        java.sql.Date secondaData= new java.sql.Date(endDate.getTime());
        QOrdineacquisto ordineacquisto = QOrdineacquisto.ordineacquisto;
        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);

       List<Ordinedettaglio> ordinedettaglios = queryFactory
               .selectFrom(ordinedettaglio)
               .join(ordinedettaglio.oOrdineAcquisto, ordineacquisto)
               .where(
                       ordineacquisto.data.after(primaData)
                               .and(ordineacquisto.data.before(secondaData))
               )
               .fetch();
        ArrayList<Sottocategoria> list = (ArrayList<Sottocategoria>) repo.findAll();
        ArrayList<Spesainvestimento> listSpese = (ArrayList<Spesainvestimento>) repoSpesa.findAll();
       for (Sottocategoria sottocategoria: list
            ) {
           Float budgetSpeso = getBudgetSpeso(sottocategoria, listSpese, ordinedettaglios);
           sottocategoria.setBudgetSpeso(budgetSpeso);
       }
    }

    private static Float getBudgetSpeso(Sottocategoria sottocategoria, ArrayList<Spesainvestimento> listSpese, List<Ordinedettaglio> ordinedettaglios) {
        float budgetSpeso = 0;
        for (Spesainvestimento spesainvestimento : listSpese
        ) {
            if (sottocategoria.getId().equals(spesainvestimento.getOSottocategoria().getId())) {
                for (Ordinedettaglio ordinedettaglio1 : ordinedettaglios
                ) {
                    if (ordinedettaglio1.getOSpesaInvestimento().getId().equals(spesainvestimento.getId())) {
                        budgetSpeso = budgetSpeso + (ordinedettaglio1.getImporto() * ordinedettaglio1.getQuantita());
                    }

                }

            }
        }
        return budgetSpeso;
    }
}
