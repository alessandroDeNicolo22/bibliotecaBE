package com.europCarBE.data.entity;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Sottocategoria")
public class Sottocategoria {
    //Sezione attributi
    @Column(name = "IDSottocategoria")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVESOTTOCATEGORIA")
    @SequenceGenerator(name = "SEQCHIAVESOTTOCATEGORIA", sequenceName = "SEQCHIAVESOTTOCATEGORIA", allocationSize = 1)
    private Integer id;

    @Column(name = "Codice", length = 3)
    private String codice;

    @Column(name = "Sottocategoria")
    private String sottocategoria;

    @Column(name = "Budget")
    private Float budget;

    @Column(name = "Budgetspeso")
    private Float budgetSpeso;

    @ManyToOne
    @JoinColumn(name = "IDArea")
    private Area oArea;
}
