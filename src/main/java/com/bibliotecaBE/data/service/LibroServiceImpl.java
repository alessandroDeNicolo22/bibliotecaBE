package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.LibroRequest;
import com.bibliotecaBE.data.dto.Response.LibroResponse;
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

    @Override
    public Page<LibroResponse> getAllLibriPage(int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

        QLibro qLibro = QLibro.libro;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Libro> list = queryFactory.selectFrom(qLibro).orderBy(qLibro.id.asc()).stream().toList();
        ArrayList<LibroResponse> responses = list.stream().map(l->new LibroResponse(l.getId(),
                l.getOAutore(),
                l.getOGenere(),
                l.getOCasaeditrice(),
                l.getTitolo())).collect(Collectors.toCollection(ArrayList::new));

        int startIndex = pageIndex * pageSize;
        if (startIndex >= responses.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, list.size());
        List<LibroResponse> pageItems = responses.subList(startIndex, endIndex);

        return new PageImpl<>(pageItems, pageRequest, responses.size());
    }

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

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Boolean checkDelete(Integer id) {
        QPrenotazione prenotazione = QPrenotazione.prenotazione;
        QPrestito prestito = QPrestito.prestito;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(emanager);
        long nLibri = jpaQueryFactory.selectFrom(prenotazione).where(prenotazione.oLibro.id.eq(id)).fetch().size();
        if(nLibri == 0){
            return true;
        }
        long nLibri1 = jpaQueryFactory.selectFrom(prestito).where(prestito.oLibro.id.eq(id)).fetch().size();
        return nLibri1 == 0;
    }

}
