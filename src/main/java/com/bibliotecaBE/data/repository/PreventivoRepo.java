package com.bibliotecaBE.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotecaBE.data.entity.Prestito;

public interface PreventivoRepo extends JpaRepository<Prestito, Integer> {

}
