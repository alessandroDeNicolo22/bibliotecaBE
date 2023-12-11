package com.europCarBE.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.europCarBE.data.entity.Ordineacquisto;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface OrdineacquistoRepo extends JpaRepository<Ordineacquisto, Integer> {


    @Query(value = "SELECT * FROM Ordineacquisto oa WHERE oa.IDOrdineacquisto IN " +
            "(SELECT DISTINCT od.IDOrdineacquisto FROM Ordinediacquistodettaglio od JOIN Progetto pr ON od.IDProgetto = pr.IDProgetto " +
            "WHERE pr.IDProgetto = ?1) AND oa.Data BETWEEN ?2 AND ?3 ", nativeQuery = true)
    List<Ordineacquisto> findPageByIDProgetto(Integer id, Date startDate, Date endDate);

    @Query(value = "SELECT * FROM Ordineacquisto oa WHERE oa.IDOrdineacquisto IN " +
            "(SELECT DISTINCT od.IDOrdineacquisto FROM Ordinediacquistodettaglio od JOIN Spesainvestimento si ON od.IDSpesainvestimento = si.IDSpesainvestimento " +
            "WHERE si.IDSottocategoria = ?1) AND oa.Data BETWEEN ?2 AND ?3 ", nativeQuery = true)
    List<Ordineacquisto> findPageByIDSottocategoria(Integer id, Date startDate, Date endDate);
}
