package com.bibliotecaBE.data.repository;

import com.bibliotecaBE.data.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface OrdineacquistoRepo extends JpaRepository<Prenotazione, Integer> {


    @Query(value = "SELECT * FROM Ordineacquisto oa WHERE oa.IDOrdineacquisto IN " +
            "(SELECT DISTINCT od.IDOrdineacquisto FROM Ordinediacquistodettaglio od JOIN Progetto pr ON od.IDProgetto = pr.IDProgetto " +
            "WHERE pr.IDProgetto = ?1) AND oa.Data BETWEEN ?2 AND ?3 ", nativeQuery = true)
    List<Prenotazione> findPageByIDProgetto(Integer id, Date startDate, Date endDate);

    @Query(value = "SELECT * FROM Ordineacquisto oa WHERE oa.IDOrdineacquisto IN " +
            "(SELECT DISTINCT od.IDOrdineacquisto FROM Ordinediacquistodettaglio od JOIN Spesainvestimento si ON od.IDSpesainvestimento = si.IDSpesainvestimento " +
            "WHERE si.IDSottocategoria = ?1) AND oa.Data BETWEEN ?2 AND ?3 ", nativeQuery = true)
    List<Prenotazione> findPageByIDSottocategoria(Integer id, Date startDate, Date endDate);
}
