package com.bibliotecaBE.data.repository;

import com.bibliotecaBE.data.entity.Studente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudenteRepo extends JpaRepository<Studente, Integer> {

}
