package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.OrdinedettaglioRequest;
import com.europCarBE.data.dto.Response.OrdinedettaglioResponse;
import com.europCarBE.data.entity.Ordineacquisto;
import com.europCarBE.data.entity.Ordinedettaglio;
import com.europCarBE.data.entity.QOrdineacquisto;
import com.europCarBE.data.repository.OrdineacquistoRepo;
import com.europCarBE.data.repository.OrdinedettaglioRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;
@Service
@Transactional
public class OrdinedettaglioServiceImpl implements OrdinedettaglioService{

    @Autowired
    OrdinedettaglioRepo repo;
    @Autowired
    EntityManager entityManager;
    @Autowired
    OrdineacquistoRepo ordineRepo;


    @Override
    public void saveDettagli(ArrayList<OrdinedettaglioRequest> elencoDettagli) {
        QOrdineacquisto ordineacquisto = QOrdineacquisto.ordineacquisto;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        Integer lastCreatedId = queryFactory.select(ordineacquisto.id)
                .from(ordineacquisto)
                .orderBy(ordineacquisto.id.desc())
                .fetchFirst();
        int i =0;



        ArrayList<Ordinedettaglio> list = elencoDettagli.stream().map(o-> new Ordinedettaglio(o.getId(),
                o.getOSpesaInvestimento(),
                o.getOProgetto(),
                o.getOOrdineAcquisto(),
                o.getImporto(),
                o.getQuantita())).collect(Collectors.toCollection(ArrayList::new));

        if(list.get(i).getOOrdineAcquisto().getId().equals(0)){
            Ordineacquisto ordineacquisto1 = ordineRepo.getReferenceById(lastCreatedId);
            for (Ordinedettaglio ordinedettaglio: list
                 ) {
               ordinedettaglio.setOOrdineAcquisto(ordineacquisto1);
            }
        }
        repo.saveAll(list);

    }
}
