package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.PrestitoRequest;
import com.bibliotecaBE.data.dto.Response.PrestitoResponse;
import com.bibliotecaBE.data.entity.Prestito;
import com.bibliotecaBE.data.entity.QFatturadettaglio;
import com.bibliotecaBE.data.entity.QPreventivo;
import com.bibliotecaBE.data.repository.PrestitoRepo;
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
public class PrestitoServiceImpl implements PrestitoService {

    @Autowired
    EntityManager emanager;

    @Autowired
    PrestitoRepo repo;

    public ArrayList<PrestitoResponse> getAllPrestiti(){
        return this.entitiesToDTO((ArrayList<Prestito>) this.repo.findAll());
    }

    private ArrayList<PrestitoResponse> entitiesToDTO(ArrayList<Prestito> elenco) {
        ArrayList<PrestitoResponse> elencoResponse = new ArrayList<>();
        for(Prestito oPrestito :elenco){
            elencoResponse.add(new PrestitoResponse(oPrestito.getId(), oPrestito.getIdDestinatario(), oPrestito.getOLibro(),
                    oPrestito.getDestinatario(), oPrestito.getDataInizio(), oPrestito.getDataFine(), oPrestito.getDataRestituzione()));
        }
        return elencoResponse;
    }

//    @Override
//    public Page<PrestitoResponse> getPagePerIdFornitore(Integer id, Integer pageIndex, Integer pageSize) {
//        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//        List<Prestito> preventivi = this.getPreventivi(id);
//        ArrayList<PrestitoResponse> elencoResponse = preventivi.stream().map(a->new PrestitoResponse(a.getId(), a.getCodice(), a.getPreventivo(), a.getOGenere(),
//                        a.getImporto(), a.getData())).collect(Collectors.toCollection(ArrayList::new));
//
//        int startIndex = pageIndex * pageSize;
//        if (startIndex >= elencoResponse.size()) {
//            return Page.empty();
//        }
//        int endIndex = Math.min(startIndex + pageSize, preventivi.size());
//        List<PrestitoResponse> pageItems = elencoResponse.subList(startIndex, endIndex);
//
//        return new PageImpl<>(pageItems, pageRequest, elencoResponse.size());
//    }

    @Override
    public PrestitoResponse findById(Integer id) {
        Prestito oPrestito = this.repo.getReferenceById(id);
        return new PrestitoResponse(oPrestito.getId(),
                oPrestito.getIdDestinatario(),
                oPrestito.getOLibro(),
                oPrestito.getDestinatario(),
                oPrestito.getDataInizio(),
                oPrestito.getDataFine(),
                oPrestito.getDataRestituzione());
    }

    @Override
    public void save(PrestitoRequest oPrestitoRequest) {
        if(oPrestitoRequest.getId() == null){
            this.repo.save(new Prestito(null, oPrestitoRequest.getIdDestinatario(), oPrestitoRequest.getOLibro(),
                    oPrestitoRequest.getDestinatario(), oPrestitoRequest.getDataInizio(), oPrestitoRequest.getDataFine(),
                    oPrestitoRequest.getDataRestituzione()));
        }else{
            this.repo.save(this.dtoUpdateEntity(oPrestitoRequest));
        }
    }

    private Prestito dtoUpdateEntity(PrestitoRequest prestitoRequest) {
        Prestito prestito = this.repo.findById(prestitoRequest.getId()).get();

        if(prestitoRequest.getDataFine() != null){
            prestito.setDataFine(prestitoRequest.getDataFine());
        }

        if(prestitoRequest.getDestinatario() != null){
            prestito.setDestinatario(prestitoRequest.getDestinatario());
        }

        if(prestitoRequest.getDataInizio() != null){
            prestito.setDataInizio(prestitoRequest.getDataInizio());
        }

        if(prestitoRequest.getIdDestinatario() != null){
            prestito.setIdDestinatario(prestitoRequest.getIdDestinatario());
        }

        if(prestitoRequest.getDataRestituzione() != null){
            prestito.setDataRestituzione(prestitoRequest.getDataRestituzione());
        }

        return prestito;
    }

//    @Override
//    public Boolean checkDelete(Integer id) {
//        QFatturadettaglio fatturadettaglio = QFatturadettaglio.fatturadettaglio;
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        long nDettagli = queryFactory.selectFrom(fatturadettaglio).where(fatturadettaglio.oPreventivo.id.eq(id)).fetch().size();
//
//        if(nDettagli == 0){
//            return true;
//        }else{
//            return false;
//        }
//
//    }

    @Override
    public void deleteById(Integer id) {
        this.repo.deleteById(id);
    }

//    private List<Prestito> getPreventivi(Integer id) {
//        QPreventivo oPreventivo = QPreventivo.preventivo1;
//        JPAQueryFactory queryFactory = new JPAQueryFactory(emanager);
//        return queryFactory.selectFrom(oPreventivo).where(oPreventivo.oFornitore.id.eq(id)).fetch();
//    }


}
