package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.StudenteRequest;
import com.bibliotecaBE.data.dto.Response.ProfessoreResponse;
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

    @Override
    public Page<StudenteResponse> getPageStudenti(int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

        QStudente studente = QStudente.studente;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);

        List<Studente> studenteList =  queryFactory.selectFrom(studente).orderBy(studente.id.asc()).fetch();
        ArrayList<StudenteResponse> elencoResponse = studenteList.stream().map(a->new StudenteResponse(a.getId(),
                a.getCognome(), a.getNome(), a.getMatricola(), a.getDataDiNascita(), a.getIndirizzo(), a.getComune(),
                a.getProvincia(), a.getNazione(), a.getTelefono())).collect(Collectors.toCollection(ArrayList::new));

        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, studenteList.size());
        List<StudenteResponse> pageItems = elencoResponse.subList(startIndex, endIndex);

        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }

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

    @Override
    public Boolean check(Integer id) {
        long studentePerPrenotazione, studentePerPrestito;

        QPrenotazione prenotazione = QPrenotazione.prenotazione;
        JPAQueryFactory queryFactoryPrenotazione = new JPAQueryFactory(emanager);
        studentePerPrenotazione = queryFactoryPrenotazione.selectFrom(prenotazione).where(prenotazione.richiedente.eq("S").and(prenotazione.idRichiedente.eq(id))).fetch().size();

        QPrestito prestito = QPrestito.prestito;
        JPAQueryFactory queryFactoryPrestito = new JPAQueryFactory(emanager);
        studentePerPrestito = queryFactoryPrestito.selectFrom(prestito).where(prestito.destinatario.eq("S").and(prestito.idDestinatario.eq(id))).fetch().size();

        return studentePerPrenotazione == 0 && studentePerPrestito == 0;
    }
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
