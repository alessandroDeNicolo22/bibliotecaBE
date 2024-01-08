package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.GenereRequest;
import com.bibliotecaBE.data.dto.Response.CopiaResponse;
import com.bibliotecaBE.data.dto.Response.GenereResponse;
import com.bibliotecaBE.data.entity.*;
import com.bibliotecaBE.data.repository.CopiaRepo;
import com.bibliotecaBE.data.repository.GenereRepo;
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
public class GenereServiceImpl implements GenereService {
    @Autowired
    GenereRepo repo;

    @Autowired
    EntityManager emanager;

    @Override
    public ArrayList<GenereResponse> getAll() {
        ArrayList<Genere> list = (ArrayList<Genere>) repo.findAll();
        return list.stream().map(f -> new GenereResponse(f.getId(),
                f.getNome())).collect(Collectors.toCollection(ArrayList::new));
    }

//    @Override
//    public Page<GenereResponse> getAllFatture(int id, int pageIndex, int pageSize) {
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//        QFatturapassiva fatturapassiva = QFatturapassiva.fatturapassiva;
//        List<Libro> list = queryFactory.selectFrom(fatturapassiva).where(fatturapassiva.oFornitore.id.eq(id)).
//                orderBy(fatturapassiva.id.asc()).stream().toList();
//        return getFatturapassivaResponses(pageIndex, pageSize, pageRequest, list);
//    }

    @Override
    public GenereResponse getGenereById(Integer id) {
        Genere genere = repo.getReferenceById(id);
        return new GenereResponse(genere.getId(),
                genere.getNome());
    }

    @Override
    public void save(GenereRequest genereRequest) {
        Genere genere = new Genere(genereRequest.getId(),
                genereRequest.getNome());
        repo.save(genere);
    }

//    @Override
//    public void deleteById(Integer id) {
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        QFatturadettaglio qFatturadettaglio = QFatturadettaglio.fatturadettaglio;
//        queryFactory.delete(qFatturadettaglio).where(qFatturadettaglio.oFatturapassiva.id.eq(id)).execute();
//        repo.deleteById(id);
//    }

}
