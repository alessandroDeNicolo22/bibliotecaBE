package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.PrenotazioneRequest;
import com.bibliotecaBE.data.dto.Response.PrenotazioneResponse;
import com.bibliotecaBE.data.entity.Prenotazione;
import com.bibliotecaBE.data.entity.QOrdineacquisto;
import com.bibliotecaBE.data.repository.PrenotazioneRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class PrenotazioneServiceImpl implements PrenotazioneService {

    @Autowired
    EntityManager entityManager;
    @Autowired
    PrenotazioneRepo prenotazioneRepo;

    @Override
    public List<PrenotazioneResponse> getAll() {
        List<Prenotazione> list = prenotazioneRepo.findAll();
        return list.stream().map(p->new PrenotazioneResponse(p.getId(),
                p.getOLibro(),
                p.getIdRichiedente(),
                p.getRichiedente(),
                p.getData(),
                p.getEvasa())).collect(Collectors.toList());
    }

    @Override
    public PrenotazioneResponse getPrenotazioneById(Integer id) {
        Prenotazione prenotazione = prenotazioneRepo.getReferenceById(id);
        return new PrenotazioneResponse(prenotazione.getId(),
                prenotazione.getOLibro(),
                prenotazione.getIdRichiedente(),
                prenotazione.getRichiedente(),
                prenotazione.getData(),
                prenotazione.getEvasa());
    }

    @Override
    public void save(PrenotazioneRequest prenotazioneRequest) {
        Prenotazione prenotazione = new Prenotazione(prenotazioneRequest.getId(),
                prenotazioneRequest.getOLibro(),
                prenotazioneRequest.getIdRichiedente(),
                prenotazioneRequest.getRichiedente(),
                prenotazioneRequest.getData(),
                prenotazioneRequest.getEvasa());
        prenotazioneRepo.save(prenotazione);

    }

    @Override
    public void deleteById(Integer id) {
        prenotazioneRepo.deleteById(id);
    }
}
