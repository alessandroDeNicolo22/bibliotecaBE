package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.SottocategoriaRequest;
import com.bibliotecaBE.data.dto.Response.SottocategoriaResponse;
import com.bibliotecaBE.data.entity.*;
import com.bibliotecaBE.data.repository.OrdinedettaglioRepo;
import com.bibliotecaBE.data.repository.SottocategoriaRepo;
import com.bibliotecaBE.data.repository.SpesainvestimentoRepo;
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
        List<Studente> studenteList = queryFactory.selectFrom(sottocategoria).orderBy(sottocategoria.id.asc()).fetch();
        return getSottocategoriaResponses(pageIndex, pageSize, pageRequest, studenteList);
    }

    private Page<SottocategoriaResponse> getSottocategoriaResponses(int pageIndex, int pageSize, PageRequest pageRequest, List<Studente> studenteList) {
        ArrayList<SottocategoriaResponse> elencoResponse = studenteList.stream().map(s -> new SottocategoriaResponse(
                s.getId(),
                s.getCodice(),
                s.getSottocategoria(),
                s.getBudget(),
                s.getBudgetSpeso(),
                s.getOCasaeditrice())).collect(Collectors.toCollection(ArrayList::new));

        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, studenteList.size());
        List<SottocategoriaResponse> pageItems = elencoResponse.subList(startIndex, endIndex);

        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }

    @Override
    public ArrayList<SottocategoriaResponse> getAllSottocategoria() {
        ArrayList<Studente> list = (ArrayList<Studente>) repo.findAll();
        return list.stream().map(s -> new SottocategoriaResponse(
                s.getId(),
                s.getCodice(),
                s.getSottocategoria(),
                s.getBudget(),
                s.getBudgetSpeso(),
                s.getOCasaeditrice()
        )).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public SottocategoriaResponse getSottocategoriaById(Integer id) {
        Studente studente = repo.getReferenceById(id);
        return new SottocategoriaResponse(
                studente.getId(),
                studente.getCodice(),
                studente.getSottocategoria(),
                studente.getBudget(),
                studente.getBudgetSpeso(),
                studente.getOCasaeditrice()
        );
    }

    @Override
    public void save(SottocategoriaRequest oSottocategoriaRequest) {
        repo.save(new Studente(oSottocategoriaRequest.getId(),
                oSottocategoriaRequest.getCodice(),
                oSottocategoriaRequest.getSottocategoria(),
                oSottocategoriaRequest.getBudget(),
                oSottocategoriaRequest.getBudgetSpeso(),
                oSottocategoriaRequest.getOCasaeditrice()));
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Boolean check(Integer id) {
        QSpesainvestimento spesainvestimento = QSpesainvestimento.spesainvestimento1;
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
        List<Studente> studenteList = queryFactory.selectFrom(sottocategoria).where(sottocategoria.oArea.id.eq(id)).orderBy(sottocategoria.id.asc()).fetch();
        return getSottocategoriaResponses(pageIndex, pageSize, pageRequest, studenteList);
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
        ArrayList<Studente> list = (ArrayList<Studente>) repo.findAll();
        ArrayList<RichiestaDiAcquisto> listSpese = (ArrayList<RichiestaDiAcquisto>) repoSpesa.findAll();
       for (Studente studente : list
            ) {
           Float budgetSpeso = getBudgetSpeso(studente, listSpese, ordinedettaglios);
           studente.setBudgetSpeso(budgetSpeso);
       }
    }

    private static Float getBudgetSpeso(Studente studente, ArrayList<RichiestaDiAcquisto> listSpese, List<Ordinedettaglio> ordinedettaglios) {
        float budgetSpeso = 0;
        for (RichiestaDiAcquisto richiestaDiAcquisto : listSpese
        ) {
            if (studente.getId().equals(richiestaDiAcquisto.getOStudente().getId())) {
                for (Ordinedettaglio ordinedettaglio1 : ordinedettaglios
                ) {
                    if (ordinedettaglio1.getOSpesaInvestimento().getId().equals(richiestaDiAcquisto.getId())) {
                        budgetSpeso = budgetSpeso + (ordinedettaglio1.getImporto() * ordinedettaglio1.getQuantita());
                    }

                }

            }
        }
        return budgetSpeso;
    }
}
