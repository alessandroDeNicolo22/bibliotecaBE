package com.bibliotecaBE.data.repository;

import com.bibliotecaBE.data.entity.Genere;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotecaBE.data.entity.Libro;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface GenereRepo extends JpaRepository<Genere, Integer> {

}
