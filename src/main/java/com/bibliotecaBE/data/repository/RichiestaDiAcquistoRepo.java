package com.bibliotecaBE.data.repository;

import com.bibliotecaBE.data.entity.RichiestaDiAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotecaBE.data.entity.Studente;

public interface RichiestaDiAcquistoRepo extends JpaRepository<RichiestaDiAcquisto, Integer> {

}
