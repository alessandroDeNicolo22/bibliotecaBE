package com.bibliotecaBE.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Professore")

public class Professore {

	//************* SEZIONE ATTRIBUTI *************
	
	@Id
	@Column(name = "IDProfessore")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEPROFESSORE")
	@SequenceGenerator(name = "SEQCHIAVEPROFESSORE", sequenceName = "SEQCHIAVEPROFESSORE", allocationSize = 1)
	private Integer id;
	
	@Column(name = "Cognome")
	private String cognome;
	
	@Column(name = "Nome")
	private String nome;

	@Column(name = "Matricola")
	private Integer matricola;


}
