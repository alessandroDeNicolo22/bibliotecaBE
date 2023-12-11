package com.bibliotecaBE.data.repository;

import com.bibliotecaBE.data.entity.Genere;
import com.bibliotecaBE.data.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepo extends JpaRepository<Libro, Integer> {

}
