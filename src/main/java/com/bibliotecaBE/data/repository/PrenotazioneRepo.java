package com.bibliotecaBE.data.repository;

import com.bibliotecaBE.data.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Integer> {
}
