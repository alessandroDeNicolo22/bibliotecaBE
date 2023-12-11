package com.europCarBE.data.service;

import com.europCarBE.data.dto.Request.PreventivoRequest;
import com.europCarBE.data.dto.Response.PreventivoResponse;
import com.europCarBE.data.entity.Preventivo;
import com.europCarBE.data.entity.QFatturadettaglio;
import com.europCarBE.data.entity.QPreventivo;
import com.europCarBE.data.repository.PreventivoRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return this.entitiesToDTO((ArrayList<Preventivo>) this.preventivoRepo.findAll());
    }

    private ArrayList<PreventivoResponse> entitiesToDTO(ArrayList<Preventivo> elenco) {
        ArrayList<PreventivoResponse> elencoResponse = new ArrayList<>();
        for(Preventivo oPreventivo:elenco){
            elencoResponse.add(new PreventivoResponse(oPreventivo.getId(), oPreventivo.getCodice(), oPreventivo.getPreventivo(),
                    oPreventivo.getOFornitore(), oPreventivo.getImporto(), oPreventivo.getData()));
        }
        return elencoResponse;
    }

    @Override
    public Page<PreventivoResponse> getPagePerIdFornitore(Integer id, Integer pageIndex, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<Preventivo> preventivi = this.getPreventivi(id);
        ArrayList<PreventivoResponse> elencoResponse = preventivi.stream().map(a->new PreventivoResponse(a.getId(), a.getCodice(), a.getPreventivo(), a.getOFornitore(),
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
        Preventivo oPreventivo = this.preventivoRepo.findById(id).get();
        return new PreventivoResponse(oPreventivo.getId(), oPreventivo.getCodice(), oPreventivo.getPreventivo(), oPreventivo.getOFornitore(), oPreventivo.getImporto(),
                oPreventivo.getData());
    }

    @Override
    public void save(PreventivoRequest oPreventivo) {
        if(oPreventivo.getId() == null){
            this.preventivoRepo.save(new Preventivo(null, oPreventivo.getCodice(), oPreventivo.getPreventivo(),
                    oPreventivo.getOFornitore(), oPreventivo.getImporto(), oPreventivo.getData()));
        }else{
            this.preventivoRepo.save(this.dtoUpdateEntity(oPreventivo));
        }
    }

    private Preventivo dtoUpdateEntity(PreventivoRequest oPreventivo) {
        Preventivo preventivo = this.preventivoRepo.findById(oPreventivo.getId()).get();

        if(oPreventivo.getCodice() != null){
            preventivo.setCodice(oPreventivo.getCodice());
        }

        if(oPreventivo.getPreventivo() != null){
            preventivo.setPreventivo(oPreventivo.getPreventivo());
        }

        if(oPreventivo.getOFornitore() != null){
            preventivo.setOFornitore(oPreventivo.getOFornitore());
        }

        if(oPreventivo.getImporto() != null){
            preventivo.setImporto(oPreventivo.getImporto());
        }

        if(oPreventivo.getData() != null){
            preventivo.setData(oPreventivo.getData());
        }

        return preventivo;
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

    private List<Preventivo> getPreventivi(Integer id) {
        QPreventivo oPreventivo = QPreventivo.preventivo1;
        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
        return queryFactory.selectFrom(oPreventivo).where(oPreventivo.oFornitore.id.eq(id)).fetch();
    }


}
