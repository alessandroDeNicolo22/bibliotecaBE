package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.SpesainvestimentoRequest;
import com.europCarBE.data.dto.Response.SpesainvestimentoResponse;
import com.europCarBE.data.entity.QFatturadettaglio;
import com.europCarBE.data.entity.QOrdinedettaglio;
import com.europCarBE.data.entity.QSpesainvestimento;
import com.europCarBE.data.entity.Spesainvestimento;
import com.europCarBE.data.repository.SpesainvestimentoRepo;
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
public class SpesainvestimentoServiceImpl implements SpesainvestimentoService{
    @Autowired
    EntityManager emanager;
    @Autowired
    SpesainvestimentoRepo repo;
    @Override
    public Page<SpesainvestimentoResponse> getAllSpeseinvestimento(int pageIndex, int pageSize) {
    return null;
    }

    @Override
    public ArrayList<SpesainvestimentoResponse> getAllSpesaInvestimento() {
        ArrayList<Spesainvestimento> list = (ArrayList<Spesainvestimento>) repo.findAll();
        return list.stream().map(spesainvestimento -> new SpesainvestimentoResponse(spesainvestimento.getId(),
                spesainvestimento.getSpesainvestimento(),
                spesainvestimento.getOSottocategoria())).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public SpesainvestimentoResponse findById(Integer id) {
        Spesainvestimento spesainvestimento = repo.getReferenceById(id);
        return new SpesainvestimentoResponse(spesainvestimento.getId(),
                spesainvestimento.getSpesainvestimento(),
                spesainvestimento.getOSottocategoria());
    }

    @Override
    public void save(SpesainvestimentoRequest oSpesaInvestimentoRequest) {
        repo.save(new Spesainvestimento(oSpesaInvestimentoRequest.getId(),oSpesaInvestimentoRequest.getSpesainvestimento(),oSpesaInvestimentoRequest.getOSottocategoria()));
    }

    @Override
    public void deleteById(Integer id) {
    repo.deleteById(id);
    }

    @Override
    public Boolean check(Integer id) {
        QOrdinedettaglio ordinedettaglio = QOrdinedettaglio.ordinedettaglio;
        QFatturadettaglio qFatturadettaglio = QFatturadettaglio.fatturadettaglio;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        long nSpese = queryFactory.selectFrom(ordinedettaglio).where(ordinedettaglio.oSpesaInvestimento.id.eq(id)).fetch().size();
        long nSpese1 = queryFactory.selectFrom(qFatturadettaglio).where(qFatturadettaglio.oSpesainvestimento.id.eq(id)).fetch().size();
        if(nSpese+nSpese1 == 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Page<SpesainvestimentoResponse> getSpesaInvestimentoByIdSottoCategoria(Integer id, Integer pageIndex, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        QSpesainvestimento spesainvestimento = QSpesainvestimento.spesainvestimento;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        List<Spesainvestimento> list =  queryFactory.selectFrom(spesainvestimento).where(spesainvestimento.oSottocategoria.id.eq(id)).orderBy(spesainvestimento.id.asc()).fetch();
        ArrayList<SpesainvestimentoResponse> elencoResponse = list.stream().map(s->new SpesainvestimentoResponse(s.getId(),
                s.getSpesainvestimento(),s.getOSottocategoria())).collect(Collectors.toCollection(ArrayList::new));

        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, list.size());
        List<SpesainvestimentoResponse> pageItems = elencoResponse.subList(startIndex, endIndex);

        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }
}
