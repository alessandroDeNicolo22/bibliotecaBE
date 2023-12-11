package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.PreventivoRequest;
import com.bibliotecaBE.data.dto.Response.PreventivoResponse;
import com.bibliotecaBE.data.entity.Prestito;
import com.bibliotecaBE.data.entity.QFatturadettaglio;
import com.bibliotecaBE.data.entity.QPreventivo;
import com.bibliotecaBE.data.repository.PreventivoRepo;
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
public class PreventivoServiceImpl implements PreventivoService{

    @Autowired
    EntityManager emanager;

    @Autowired
    PreventivoRepo preventivoRepo;

    public ArrayList<PreventivoResponse> getAllPreventivi(){
        return this.entitiesToDTO((ArrayList<Prestito>) this.preventivoRepo.findAll());
    }

    private ArrayList<PreventivoResponse> entitiesToDTO(ArrayList<Prestito> elenco) {
        ArrayList<PreventivoResponse> elencoResponse = new ArrayList<>();
        for(Prestito oPrestito :elenco){
            elencoResponse.add(new PreventivoResponse(oPrestito.getId(), oPrestito.getCodice(), oPrestito.getPreventivo(),
                    oPrestito.getOGenere(), oPrestito.getImporto(), oPrestito.getData()));
        }
        return elencoResponse;
    }

    @Override
    public Page<PreventivoResponse> getPagePerIdFornitore(Integer id, Integer pageIndex, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Prestito> preventivi = this.getPreventivi(id);
        ArrayList<PreventivoResponse> elencoResponse = preventivi.stream().map(a->new PreventivoResponse(a.getId(), a.getCodice(), a.getPreventivo(), a.getOGenere(),
                        a.getImporto(), a.getData())).collect(Collectors.toCollection(ArrayList::new));

        int startIndex = pageIndex * pageSize;
        if (startIndex >= elencoResponse.size()) {
            return Page.empty();
        }
        int endIndex = Math.min(startIndex + pageSize, preventivi.size());
        List<PreventivoResponse> pageItems = elencoResponse.subList(startIndex, endIndex);

        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
    }

    @Override
    public PreventivoResponse findById(Integer id) {
        Prestito oPrestito = this.preventivoRepo.findById(id).get();
        return new PreventivoResponse(oPrestito.getId(), oPrestito.getCodice(), oPrestito.getPreventivo(), oPrestito.getOGenere(), oPrestito.getImporto(),
                oPrestito.getData());
    }

    @Override
    public void save(PreventivoRequest oPreventivo) {
        if(oPreventivo.getId() == null){
            this.preventivoRepo.save(new Prestito(null, oPreventivo.getCodice(), oPreventivo.getPreventivo(),
                    oPreventivo.getOGenere(), oPreventivo.getImporto(), oPreventivo.getData()));
        }else{
            this.preventivoRepo.save(this.dtoUpdateEntity(oPreventivo));
        }
    }

    private Prestito dtoUpdateEntity(PreventivoRequest oPreventivo) {
        Prestito prestito = this.preventivoRepo.findById(oPreventivo.getId()).get();

        if(oPreventivo.getCodice() != null){
            prestito.setCodice(oPreventivo.getCodice());
        }

        if(oPreventivo.getPreventivo() != null){
            prestito.setPreventivo(oPreventivo.getPreventivo());
        }

        if(oPreventivo.getOGenere() != null){
            prestito.setOGenere(oPreventivo.getOGenere());
        }

        if(oPreventivo.getImporto() != null){
            prestito.setImporto(oPreventivo.getImporto());
        }

        if(oPreventivo.getData() != null){
            prestito.setData(oPreventivo.getData());
        }

        return prestito;
    }

    @Override
    public Boolean checkDelete(Integer id) {
        QFatturadettaglio fatturadettaglio = QFatturadettaglio.fatturadettaglio;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        long nDettagli = queryFactory.selectFrom(fatturadettaglio).where(fatturadettaglio.oPreventivo.id.eq(id)).fetch().size();

        if(nDettagli == 0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void deleteById(Integer id) {
        this.preventivoRepo.deleteById(id);
    }

    private List<Prestito> getPreventivi(Integer id) {
        QPreventivo oPreventivo = QPreventivo.preventivo1;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        return queryFactory.selectFrom(oPreventivo).where(oPreventivo.oFornitore.id.eq(id)).fetch();
    }


}
