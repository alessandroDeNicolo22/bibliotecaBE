package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.FatturadettaglioRequest;
import com.europCarBE.data.entity.Fatturadettaglio;
import com.europCarBE.data.entity.Fatturapassiva;
import com.europCarBE.data.entity.QFatturapassiva;
import com.europCarBE.data.repository.FatturadettaglioRepo;
import com.europCarBE.data.repository.FatturapassivaRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Transactional
public class FatturadettaglioServiceImpl implements FatturadettaglioService{

    @Autowired
    FatturadettaglioRepo repo;

    @Autowired
    EntityManager entityManager;

    @Autowired
    FatturapassivaRepo fattRepo;

    @Override
    public void saveDettagli(ArrayList<FatturadettaglioRequest> elencoDettagli) {
        QFatturapassiva fatturapassiva = QFatturapassiva.fatturapassiva;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        Integer lastCreatedId = queryFactory.select(fatturapassiva.id).
                from(fatturapassiva).orderBy(fatturapassiva.id.desc()).fetchFirst();
        ArrayList<Fatturadettaglio> list = elencoDettagli.stream().map(f-> new Fatturadettaglio(f.getId(),
                f.getOAliquotaiva(),
                f.getOFatturapassiva(),
                f.getOPreventivo(),
                f.getOSpesainvestimento(),
                f.getDettaglioFattura(),
                f.getImporto())).collect(Collectors.toCollection(ArrayList::new));
        if(list.get(0).getOFatturapassiva().getId().equals(0)){
            Fatturapassiva fatturapassiva1 = fattRepo.getReferenceById(lastCreatedId);
            for (Fatturadettaglio fatturadettaglio: list
                 ) {
                fatturadettaglio.setOFatturapassiva(fatturapassiva1);
            }
        }
        repo.saveAll(list);
    }
}
