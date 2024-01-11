package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.StudenteRequest;
import com.bibliotecaBE.data.dto.Response.StudenteResponse;
import com.bibliotecaBE.data.entity.*;
import com.bibliotecaBE.data.repository.StudenteRepo;
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
public class StudenteServiceImpl implements StudenteService {
    @Autowired
    EntityManager emanager;
    @Autowired
    StudenteRepo repo;
//    @Override
//    public Page<RichiestaDiAcquistoResponse> getAllSpeseinvestimento(int pageIndex, int pageSize) {
//    return null;
//    }

    @Override
    public ArrayList<StudenteResponse> getAllStudenti() {
        ArrayList<Studente> list = (ArrayList<Studente>) repo.findAll();
        return list.stream().map(s -> new StudenteResponse(s.getId(),
                s.getCognome(),
                s.getNome(),
                s.getMatricola(),
                s.getDataDiNascita(),
                s.getIndirizzo(),
                s.getComune(),
                s.getProvincia(),
                s.getNazione(),
                s.getTelefono())).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public StudenteResponse findById(Integer id) {
        Studente studente = repo.getReferenceById(id);
        return new StudenteResponse(studente.getId(),
                studente.getCognome(),
                studente.getNome(),
                studente.getMatricola(),
                studente.getDataDiNascita(),
                studente.getIndirizzo(),
                studente.getComune(),
                studente.getProvincia(),
                studente.getNazione(),
                studente.getTelefono());
    }

    @Override
    public void save(StudenteRequest oStudenteRequest) {
        repo.save(new Studente(oStudenteRequest.getId(),
                oStudenteRequest.getCognome(),
                oStudenteRequest.getNome(),
                oStudenteRequest.getMatricola(),
                oStudenteRequest.getDataDiNascita(),
                oStudenteRequest.getIndirizzo(),
                oStudenteRequest.getComune(),
                oStudenteRequest.getProvincia(),
                oStudenteRequest.getNazione(),
                oStudenteRequest.getTelefono()));
    }

    @Override
    public void deleteById(Integer id) {
    repo.deleteById(id);
    }

//    @Override
//    public Boolean check(Integer id) {
//        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
//        QFatturadettaglio qFatturadettaglio = QFatturadettaglio.fatturadettaglio;
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        long nSpese = queryFactory.selectFrom(ordinedettaglio).where(ordinedettaglio.oSpesaInvestimento.id.eq(id)).fetch().size();
//        long nSpese1 = queryFactory.selectFrom(qFatturadettaglio).where(qFatturadettaglio.oSpesainvestimento.id.eq(id)).fetch().size();
//        if(nSpese+nSpese1 == 0){
//            return true;
//        }else {
//            return false;
//        }
//    }
//
//    @Override
//    public Page<RichiestaDiAcquistoResponse> getSpesaInvestimentoByIdSottoCategoria(Integer id, Integer pageIndex, Integer pageSize) {
//        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//        QSpesainvestimento spesainvestimento = QSpesainvestimento.spesainvestimento1;
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        List<RichiestaDiAcquisto> list =  queryFactory.selectFrom(spesainvestimento).where(spesainvestimento.oSottocategoria.id.eq(id)).orderBy(spesainvestimento.id.asc()).fetch();
//        ArrayList<RichiestaDiAcquistoResponse> elencoResponse = list.stream().map(s->new RichiestaDiAcquistoResponse(s.getId(),
//                s.getSpesainvestimento(),s.getOStudente())).collect(Collectors.toCollection(ArrayList::new));
//
//        int startIndex = pageIndex * pageSize;
//        if (startIndex >= elencoResponse.size()) {
//            return Page.empty();
//        }
//        int endIndex = Math.min(startIndex + pageSize, list.size());
//        List<RichiestaDiAcquistoResponse> pageItems = elencoResponse.subList(startIndex, endIndex);
//
//        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
//    }
}
