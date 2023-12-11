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
@Table(name = "Casaeditrice")
public class Casaeditrice {

	//************* SEZIONE ATTRIBUTI **********

	@Column(name = "IDCasaeditrice")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVECASAEDITRICE")
	@SequenceGenerator(name = "SEQCHIAVECASAEDITRICE", sequenceName = "SEQCHIAVECASAEDITRICE", allocationSize = 1)
	private Integer id;

	@Column(name = "Nome")
	private String codice;

}

