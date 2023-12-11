package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.FilterDateRequest;
import com.bibliotecaBE.data.dto.Request.OrdineacquistoRequest;
import com.bibliotecaBE.data.dto.Response.LibroResponse;
import com.bibliotecaBE.data.dto.Response.OrdinedettaglioResponse;
import com.bibliotecaBE.data.entity.*;
import com.bibliotecaBE.data.repository.PrenotazioneRepo;
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
public class OrdineacquistoServiceImpl implements OrdineacquistoService{
   @Autowired
   PrenotazioneRepo repo;

   @Autowired
   EntityManager emanager;

   @Autowired
    OrdinedettaglioRepo dettaglioRepo;

    @Override
    public ArrayList<LibroResponse> getAll() {
        ArrayList<Prenotazione> list = (ArrayList<Prenotazione>) repo.findAll();
        return list.stream().map(o->new LibroResponse(o.getId(),
                o.getImporto(),
                o.getOrdineAcquisto(),
                o.getData(),
                o.getOGenere())).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Page<LibroResponse> getAllOrdini(int id, int pageIndex, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QOrdineacquisto ordineacquisto = QOrdineacquisto.ordineacquisto;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Prenotazione> list = queryFactory.selectFrom(ordineacquisto).where(ordineacquisto.oFornitore.id.eq(id)).orderBy(ordineacquisto.id.asc()).stream().toList();
        return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);
    }

    private Page<LibroResponse> getOrdineacquistoResponses(int pageIndex, int pageSize, PageRequest pageRequest, List<Prenotazione> list) {
        ArrayList<LibroResponse> elencoResponse = list.stream().map(o -> new LibroResponse(
                o.getId(),
                o.getImporto(),
                o.getOrdineAcquisto(),
                o.getData(),
                o.getOGenere())).collect(Collectors.toCollection(ArrayList::new));

        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, list.size());
        List<LibroResponse> pageItems = elencoResponse.subList(startIndex, endIndex);

        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }

    @Override
    public LibroResponse getOrdineById(Integer id) {
        Prenotazione prenotazione = repo.getReferenceById(id);
        return new LibroResponse(prenotazione.getId(),
                prenotazione.getImporto(),
                prenotazione.getOrdineAcquisto(),
                prenotazione.getData(),
                prenotazione.getOGenere());
    }

    @Override
    public void save(OrdineacquistoRequest ordineacquistoRequest) {
        if (ordineacquistoRequest.getId() != 0) {
            QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
            JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
            queryFactory.delete(ordinedettaglio).where(ordinedettaglio.oOrdineAcquisto.id.eq(ordineacquistoRequest.getId())).execute();
            repo.save(new Prenotazione(ordineacquistoRequest.getId(),
                    ordineacquistoRequest.getImporto(),
                    ordineacquistoRequest.getOrdineacquisto(),
                    ordineacquistoRequest.getData(),
                    ordineacquistoRequest.getOGenere()));
        } else {
            repo.save(new Prenotazione(0,
                    ordineacquistoRequest.getImporto(),
                    ordineacquistoRequest.getOrdineacquisto(),
                    ordineacquistoRequest.getData(),
                    ordineacquistoRequest.getOGenere()));
        }
    }

    @Override
    public void deleteById(Integer id) {
        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        queryFactory.delete(ordinedettaglio).where(ordinedettaglio.oOrdineAcquisto.id.eq(id)).execute();
        repo.deleteById(id);
    }

    @Override
    public Page<OrdinedettaglioResponse> findDettagli(Integer id, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Ordinedettaglio> list = queryFactory.selectFrom(ordinedettaglio).where(ordinedettaglio.oOrdineAcquisto.id.eq(id)).orderBy(ordinedettaglio.id.asc()).stream().toList();
        ArrayList<OrdinedettaglioResponse> elencoResponse = list.stream().map(o -> new OrdinedettaglioResponse(o.getId(),
                o.getOSpesaInvestimento(),
                o.getOProfessore(),
                o.getOOrdineAcquisto(),
                o.getImporto(),
                o.getQuantita())).collect(Collectors.toCollection(ArrayList::new));
        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, list.size());
        List<OrdinedettaglioResponse> pageItems = elencoResponse.subList(startIndex, endIndex);
        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }

    @Override
    public ArrayList<OrdinedettaglioResponse> findDettagli1(Integer id) {
        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Ordinedettaglio> list = queryFactory.selectFrom(ordinedettaglio).where(ordinedettaglio.oOrdineAcquisto.id.eq(id)).orderBy(ordinedettaglio.id.asc()).stream().toList();
        return  list.stream().map(o -> new OrdinedettaglioResponse(o.getId(),
                o.getOSpesaInvestimento(),
                o.getOProfessore(),
                o.getOOrdineAcquisto(),
                o.getImporto(),
                o.getQuantita())).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Page<LibroResponse> filterOrdini(FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QOrdineacquisto ordineacquisto = QOrdineacquisto.ordineacquisto;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        if(filterDateRequest.getType().equals("Fornitore")) {
            List<Prenotazione> list = queryFactory.selectFrom(ordineacquisto).where(ordineacquisto.oFornitore.id.eq(filterDateRequest.getId()).
                    and(ordineacquisto.data.after(filterDateRequest.getStartDate()).
                            and(ordineacquisto.data.before(filterDateRequest.getEndDate())))).stream().toList();
            return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);

        }else if(filterDateRequest.getType().equals("Progetto")){
            List<Prenotazione> list = repo.findPageByIDProgetto(filterDateRequest.getId(), filterDateRequest.getStartDate(), filterDateRequest.getEndDate());
            return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);
        }else if(filterDateRequest.getType().equals("Sottocategoria")){
            List<Prenotazione> list = repo.findPageByIDSottocategoria(filterDateRequest.getId(), filterDateRequest.getStartDate(), filterDateRequest.getEndDate());
            return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);
        }else{
            return null;
        }
    }

    @Override
    public Page<LibroResponse> filterOnlyDate(FilterDateRequest filterDateRequest, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QOrdineacquisto ordineacquisto = QOrdineacquisto.ordineacquisto;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Prenotazione> list = queryFactory.selectFrom(ordineacquisto).where(ordineacquisto.data.between(filterDateRequest.getStartDate(),filterDateRequest.getEndDate())).stream().toList();
        return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);
    }
}
