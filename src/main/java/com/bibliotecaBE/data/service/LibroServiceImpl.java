package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.LibroRequest;
import com.bibliotecaBE.data.dto.Response.LibroResponse;
import com.bibliotecaBE.data.dto.Response.PrenotazioneResponse;
import com.bibliotecaBE.data.entity.*;
import com.bibliotecaBE.data.repository.LibroRepo;
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
public class LibroServiceImpl implements LibroService {
   @Autowired
    LibroRepo repo;

   @Autowired
   EntityManager emanager;

    @Override
    public ArrayList<LibroResponse> getAll() {
        ArrayList<Libro> list = (ArrayList<Libro>) repo.findAll();
        return list.stream().map(o->new LibroResponse(o.getId(),
                o.getOAutore(),
                o.getOGenere(),
                o.getOCasaeditrice(),
                o.getTitolo())).collect(Collectors.toCollection(ArrayList::new));
    }

//    @Override
//    public Page<LibroResponse> getAllOrdini(int id, int pageIndex, int pageSize) {
//
//        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//        QOrdineacquisto ordineacquisto = QOrdineacquisto.ordineacquisto;
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        List<Prenotazione> list = queryFactory.selectFrom(ordineacquisto).where(ordineacquisto.oFornitore.id.eq(id)).orderBy(ordineacquisto.id.asc()).stream().toList();
//        return getOrdineacquistoResponses(pageIndex, pageSize, pageRequest, list);
//    }

//    private Page<LibroResponse> getOrdineacquistoResponses(int pageIndex, int pageSize, PageRequest pageRequest, List<Prenotazione> list) {
//        ArrayList<LibroResponse> elencoResponse = list.stream().map(o -> new LibroResponse(
//                o.getId(),
//                o.getImporto(),
//                o.getOrdineAcquisto(),
//                o.getData(),
//                o.getOGenere())).collect(Collectors.toCollection(ArrayList::new));
//
//        int startIndex = pageIndex * pageSize;
//        if (startIndex >= elencoResponse.size()) {
//            return Page.empty();
//        }
//        int endIndex = Math.min(startIndex + pageSize, list.size());
//        List<LibroResponse> pageItems = elencoResponse.subList(startIndex, endIndex);
//
//        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
//    }

    @Override
    public LibroResponse getLibroById(Integer id) {
        Libro libro = repo.getReferenceById(id);
        return new LibroResponse(libro.getId(),
                libro.getOAutore(),
                libro.getOGenere(),
                libro.getOCasaeditrice(),
                libro.getTitolo());
    }

    @Override
    public void save(LibroRequest libroRequest) {
        Libro libro = new Libro(libroRequest.getId(),libroRequest.getOAutore(),libroRequest.getOGenere(),libroRequest.getOCasaeditrice(),libroRequest.getTitolo());
        repo.save(libro);
    }

//    @Override
//    public void deleteById(Integer id) {
//        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        queryFactory.delete(ordinedettaglio).where(ordinedettaglio.oOrdineAcquisto.id.eq(id)).execute();
//        repo.deleteById(id);
//    }

}
