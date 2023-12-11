package com.europCarBE.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.europCarBE.data.entity.Ordinedettaglio;

import java.util.List;

public interface OrdinedettaglioRepo extends JpaRepository<Ordinedettaglio, Integer> {

}
