package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.PrenotazioneRequest;
import com.bibliotecaBE.data.dto.Response.PrenotazioneResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface PrenotazioneService {
public List<PrenotazioneResponse> getAll();
public PrenotazioneResponse getPrenotazioneById(Integer id);
public void save(PrenotazioneRequest prenotazioneRequest);

public void deleteById(Integer id);
}
