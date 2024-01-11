package com.bibliotecaBE.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Studente")
public class Studente {
    //Sezione attributi
    @Column(name = "IDStudente")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVESTUDENTE")
    @SequenceGenerator(name = "SEQCHIAVESTUDENTE", sequenceName = "SEQCHIAVESTUDENTE", allocationSize = 1)
    private Integer id;

    @Column(name = "Cognome")
    private String cognome;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Matricola")
    private Integer matricola;

    @Column(name = "Datadinascita")
    private Date dataDiNascita;

    @Column(name = "Indirizzo")
    private String indirizzo;

    @Column(name = "Comune")
    private String comune;

    @Column(name = "Provincia", length = 2)
    private String provincia;

    @Column(name = "Nazione")
    private String nazione;

    @Column(name = "Telefono")
    private Integer telefono;
}
