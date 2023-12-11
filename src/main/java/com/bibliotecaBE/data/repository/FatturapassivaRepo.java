package com.bibliotecaBE.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotecaBE.data.entity.Libro;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface FatturapassivaRepo extends JpaRepository<Libro, Integer> {
    @Query(value="SELECT * FROM Fatturapassiva fp WHERE fp.IDFatturapassiva IN (SELECT DISTINCT fpd.IDFatturapassiva FROM Fatturapassivadettaglio fpd "
            + "JOIN Spesainvestimento si ON fpd.IDSpesainvestimento = si.IDSpesainvestimento "
            + "WHERE si.IDSottocategoria = ?3) AND fp.Data BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Libro> findPageByIDSottocategoria(Date date, Date date2, int id);

    @Query(value="SELECT * FROM Fatturapassiva fp WHERE fp.IDFatturapassiva IN (SELECT DISTINCT fpd.IDFatturapassiva FROM Fatturapassivadettaglio fpd WHERE fpd.IDPreventivo = ?3) AND fp.Data BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Libro> findPageByIDPreventivo(Date date, Date date2, int id);
}
