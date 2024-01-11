package com.bibliotecaBE.data.repository;

import com.bibliotecaBE.data.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente,Integer> {
Utente findByEmail(String email);
}
