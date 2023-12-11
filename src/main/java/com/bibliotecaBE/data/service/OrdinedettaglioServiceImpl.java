package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.OrdinedettaglioRequest;
import com.bibliotecaBE.data.entity.Prenotazione;
import com.bibliotecaBE.data.entity.QOrdineacquisto;
import com.bibliotecaBE.data.repository.PrenotazioneRepo;
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
    PrenotazioneRepo ordineRepo;


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
                o.getOProfessore(),
                o.getOOrdineAcquisto(),
                o.getImporto(),
                o.getQuantita())).collect(Collectors.toCollection(ArrayList::new));

        if(list.get(i).getOOrdineAcquisto().getId().equals(0)){
            Prenotazione prenotazione1 = ordineRepo.getReferenceById(lastCreatedId);
            for (Ordinedettaglio ordinedettaglio: list
                 ) {
               ordinedettaglio.setOOrdineAcquisto(prenotazione1);
            }
        }
        repo.saveAll(list);

    }
}
