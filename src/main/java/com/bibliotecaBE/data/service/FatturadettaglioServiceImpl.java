package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.FatturadettaglioRequest;
import com.bibliotecaBE.data.entity.Copia;
import com.bibliotecaBE.data.entity.Libro;
import com.bibliotecaBE.data.entity.QFatturapassiva;
import com.bibliotecaBE.data.repository.CopiaRepo;
import com.bibliotecaBE.data.repository.GenereRepo;
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
    CopiaRepo repo;

    @Autowired
    EntityManager entityManager;

    @Autowired
    GenereRepo fattRepo;

    @Override
    public void saveDettagli(ArrayList<FatturadettaglioRequest> elencoDettagli) {
        QFatturapassiva fatturapassiva = QFatturapassiva.fatturapassiva;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        Integer lastCreatedId = queryFactory.select(fatturapassiva.id).
                from(fatturapassiva).orderBy(fatturapassiva.id.desc()).fetchFirst();
        ArrayList<Copia> list = elencoDettagli.stream().map(f-> new Copia(f.getId(),
                f.getOAutore(),
                f.getOLibro(),
                f.getOPrestito(),
                f.getORichiestaDiAcquisto(),
                f.getDettaglioFattura(),
                f.getImporto())).collect(Collectors.toCollection(ArrayList::new));
        if(list.get(0).getOFatturapassiva().getId().equals(0)){
            Libro libro1 = fattRepo.getReferenceById(lastCreatedId);
            for (Copia copia : list
                 ) {
                copia.setOFatturapassiva(libro1);
            }
        }
        repo.saveAll(list);
    }
}
