package com.europCarBE.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.europCarBE.data.entity.Fatturapassiva;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface FatturapassivaRepo extends JpaRepository<Fatturapassiva, Integer> {
    @Query(value="SELECT * FROM Fatturapassiva fp WHERE fp.IDFatturapassiva IN (SELECT DISTINCT fpd.IDFatturapassiva FROM Fatturapassivadettaglio fpd "
            + "JOIN Spesainvestimento si ON fpd.IDSpesainvestimento = si.IDSpesainvestimento "
            + "WHERE si.IDSottocategoria = ?3) AND fp.Data BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Fatturapassiva> findPageByIDSottocategoria(Date date, Date date2, int id);

    @Query(value="SELECT * FROM Fatturapassiva fp WHERE fp.IDFatturapassiva IN (SELECT DISTINCT fpd.IDFatturapassiva FROM Fatturapassivadettaglio fpd WHERE fpd.IDPreventivo = ?3) AND fp.Data BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Fatturapassiva> findPageByIDPreventivo(Date date, Date date2, int id);
}
