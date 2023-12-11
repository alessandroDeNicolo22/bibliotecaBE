package com.europCarBE.data.entity;

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
@Table(name = "Progetto")

public class Progetto {

	//************* SEZIONE ATTRIBUTI *************
	
	@Id
	@Column(name = "IDProgetto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEPROGETTO")
	@SequenceGenerator(name = "SEQCHIAVEPROGETTO", sequenceName = "SEQCHIAVEPROGETTO", allocationSize = 1)
	private Integer id;
	
	@Column(name = "Codice")
	private String codice;
	
	@Column(name = "Progetto")
	private String progetto;


}
