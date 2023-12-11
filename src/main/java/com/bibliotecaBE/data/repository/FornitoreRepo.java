package com.bibliotecaBE.data.repository;

import com.bibliotecaBE.data.entity.Genere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornitoreRepo extends JpaRepository<Genere, Integer> {

}
