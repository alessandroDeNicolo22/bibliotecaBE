package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.RichiestaDiAcquistoRequest;
import com.bibliotecaBE.data.dto.Response.RichiestaDiAcquistoResponse;
import com.bibliotecaBE.data.entity.*;
import com.bibliotecaBE.data.repository.RichiestaDiAcquistoRepo;
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
public class RichiestaDiAcquistoServiceImpl implements RichiestaDiAcquistoService {
    @Autowired
    RichiestaDiAcquistoRepo repo;
    @Autowired
    EntityManager emanager;

//    @Override
//    public Page<StudenteResponse> getAllSottocategoriaPage(int pageIndex, int pageSize) {
//        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//        QSottocategoria sottocategoria = QSottocategoria.sottocategoria1;
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        List<Studente> studenteList = queryFactory.selectFrom(sottocategoria).orderBy(sottocategoria.id.asc()).fetch();
//        return getSottocategoriaResponses(pageIndex, pageSize, pageRequest, studenteList);
//    }

//    private Page<StudenteResponse> getSottocategoriaResponses(int pageIndex, int pageSize, PageRequest pageRequest, List<Studente> studenteList) {
//        ArrayList<StudenteResponse> elencoResponse = studenteList.stream().map(s -> new StudenteResponse(
//                s.getId(),
//                s.getCodice(),
//                s.getSottocategoria(),
//                s.getBudget(),
//                s.getBudgetSpeso(),
//                s.getOCasaeditrice())).collect(Collectors.toCollection(ArrayList::new));
//
//        int startIndex = pageIndex * pageSize;
//        if (startIndex >= elencoResponse.size()) {
//            return Page.empty();
//        }
//        int endIndex = Math.min(startIndex + pageSize, studenteList.size());
//        List<StudenteResponse> pageItems = elencoResponse.subList(startIndex, endIndex);
//
//        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
//    }

    @Override
    public ArrayList<RichiestaDiAcquistoResponse> getAllRichiesteDiAcquisto() {
        ArrayList<RichiestaDiAcquisto> list = (ArrayList<RichiestaDiAcquisto>) repo.findAll();
        return list.stream().map(s -> new RichiestaDiAcquistoResponse(
                s.getId(),
                s.getOProfessore(),
                s.getOLibro(),
                s.getOAutore(),
                s.getTitolo(),
                s.getOGenere(),
                s.getStato()
        )).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public RichiestaDiAcquistoResponse getRichiestaDiAcquistoById(Integer id) {
        RichiestaDiAcquisto richiestaDiAcquisto = repo.getReferenceById(id);
        return new RichiestaDiAcquistoResponse(
                richiestaDiAcquisto.getId(),
                richiestaDiAcquisto.getOProfessore(),
                richiestaDiAcquisto.getOLibro(),
                richiestaDiAcquisto.getOAutore(),
                richiestaDiAcquisto.getTitolo(),
                richiestaDiAcquisto.getOGenere(),
                richiestaDiAcquisto.getStato()
        );
    }

    @Override
    public void save(RichiestaDiAcquistoRequest oRichiestaDiAcquistoRequest) {
        repo.save(new RichiestaDiAcquisto(oRichiestaDiAcquistoRequest.getId(),
                oRichiestaDiAcquistoRequest.getOProfessore(),
                oRichiestaDiAcquistoRequest.getTitolo(),
                oRichiestaDiAcquistoRequest.getOLibro(),
                oRichiestaDiAcquistoRequest.getOAutore(),
                oRichiestaDiAcquistoRequest.getOGenere(),
                oRichiestaDiAcquistoRequest.getStato()));
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

//    @Override
//    public Boolean check(Integer id) {
//        QSpesainvestimento spesainvestimento = QSpesainvestimento.spesainvestimento1;
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        long nSottocategoria = queryFactory.selectFrom(spesainvestimento).where(spesainvestimento.oSottocategoria.id
//                .eq(id)).stream().count();
//        if(nSottocategoria == 0){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    @Override
//    public Page<StudenteResponse> getSottocategoriaByAreaPage(int id, int pageIndex, int pageSize) {
//        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//        QSottocategoria sottocategoria = QSottocategoria.sottocategoria1;
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        List<Studente> studenteList = queryFactory.selectFrom(sottocategoria).where(sottocategoria.oArea.id.eq(id)).orderBy(sottocategoria.id.asc()).fetch();
//        return getSottocategoriaResponses(pageIndex, pageSize, pageRequest, studenteList);
//    }
//
//   @Override
//    public void setBudget(Date firstDate, Date endDate) {
//        java.sql.Date primaData = new java.sql.Date(firstDate.getTime());
//        java.sql.Date secondaData= new java.sql.Date(endDate.getTime());
//        QOrdineacquisto ordineacquisto = QOrdineacquisto.ordineacquisto;
//        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//
//       List<Ordinedettaglio> ordinedettaglios = queryFactory
//               .selectFrom(ordinedettaglio)
//               .join(ordinedettaglio.oOrdineAcquisto, ordineacquisto)
//               .where(
//                       ordineacquisto.data.after(primaData)
//                               .and(ordineacquisto.data.before(secondaData))
//               )
//               .fetch();
//        ArrayList<Studente> list = (ArrayList<Studente>) repo.findAll();
//        ArrayList<RichiestaDiAcquisto> listSpese = (ArrayList<RichiestaDiAcquisto>) repoSpesa.findAll();
//       for (Studente studente : list
//            ) {
//           Float budgetSpeso = getBudgetSpeso(studente, listSpese, ordinedettaglios);
//           studente.setBudgetSpeso(budgetSpeso);
//       }
//    }
//
//    private static Float getBudgetSpeso(Studente studente, ArrayList<RichiestaDiAcquisto> listSpese, List<Ordinedettaglio> ordinedettaglios) {
//        float budgetSpeso = 0;
//        for (RichiestaDiAcquisto richiestaDiAcquisto : listSpese
//        ) {
//            if (studente.getId().equals(richiestaDiAcquisto.getOStudente().getId())) {
//                for (Ordinedettaglio ordinedettaglio1 : ordinedettaglios
//                ) {
//                    if (ordinedettaglio1.getOSpesaInvestimento().getId().equals(richiestaDiAcquisto.getId())) {
//                        budgetSpeso = budgetSpeso + (ordinedettaglio1.getImporto() * ordinedettaglio1.getQuantita());
//                    }
//
//                }
//
//            }
//        }
//        return budgetSpeso;
//    }
}
